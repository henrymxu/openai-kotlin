package com.henryxu.openaikotlin.internal

import com.henryxu.openaikotlin.OpenAiApi
import com.henryxu.openaikotlin.Response
import com.henryxu.openaikotlin.models.*
import com.henryxu.openaikotlin.services.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.resources.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal class ConcreteOpenAiService(val client: HttpClient) : OpenAiApi {
    override suspend fun listModels(): Response<ModelsResult> = get(ModelsResource())

    override suspend fun retrieveModel(modelId: String): Response<ModelResult> =
        get(ModelsResource.ModelResource(modelId))

    override suspend fun createCompletion(request: CreateCompletionRequest): Response<CompletionResult> =
        post(CompletionsResource(), request)

    override suspend fun createEdit(request: CreateEditRequest): Response<EditResult> =
        post(EditsResource(), request)

    override suspend fun createImage(request: CreateImageRequest): Response<ImageResult> =
        post(ImagesResource.ImageCreateResource(), request)

    override suspend fun editImage(request: EditImageRequest): Response<ImageResult> =
        submitForm(ImagesResource.ImageEditResource(), request)

    override suspend fun variateImage(request: VariateImageRequest): Response<ImageResult> =
        submitForm(ImagesResource.ImageVariateResource(), request)

    override suspend fun createEmbeddings(request: CreateEmbeddingsRequest): Response<EmbeddingResult> =
        post(EmbeddingsResource(), request)

    override suspend fun listFiles(): Response<FilesResult> =
        get(FilesResource())

    override suspend fun retrieveFile(fileId: String): Response<FileResult> =
        get(FilesResource.FileResource(fileId))

    override suspend fun retrieveFileContent(fileId: String): Response<Any> =
        get(FilesResource.FileContentResource(fileId))

    override suspend fun uploadFile(request: UploadFileRequest): Response<FileResult> =
        submitForm(FilesResource, request)

    override suspend fun deleteFile(fileId: String): Response<EntityDeleteResult> =
        delete(FilesResource.FileResource(fileId))

    override suspend fun createFineTune(request: CreateFineTuneRequest): Response<FineTuneResult> =
        post(FineTunesResource.FineTuneResource, request)

    override suspend fun listFineTunes(): Response<FineTunesResult> =
        get(FineTunesResource())

    override suspend fun retrieveFineTune(fineTuneId: String): Response<FineTuneResult> =
        get(FineTunesResource.FineTuneResource(fineTuneId))

    override suspend fun cancelFineTune(fineTuneId: String): Response<FineTuneResult> =
        post(FineTunesResource.FineTuneCancelResource(fineTuneId), null)

    override suspend fun listFineTuneEvents(fineTuneId: String): Response<FineTuneEventsResult> =
        get(FineTunesResource.FineTuneEventsResource(fineTuneId, stream = false))

    override suspend fun streamFineTuneEvents(fineTuneId: String): Response<FineTuneEvent> =
        get(FineTunesResource.FineTuneEventsResource(fineTuneId, stream = true))

    override suspend fun deleteFineTuneModel(modelId: String): Response<EntityDeleteResult> =
        delete(ModelsResource.ModelResource(modelId))

    override suspend fun createModeration(request: CreateModerationRequest): Response<ModerationResult> =
        post(ModerationsResource(), request)

    @Deprecated("")
    override suspend fun listEngines(): Response<EnginesResult> =
        get(EnginesResource())

    @Deprecated("")
    override suspend fun retrieveEngine(engineId: String): Response<EngineResult> =
        get(EnginesResource.EngineResource(engineId))

    private suspend inline fun <reified R: Any> makeStreamingRequest(fn: () -> HttpResponse): Response<R> {
        var producer: Flow<R>? = null
        var error: OpenAiClientRequestError? = null

        try {
            val response = fn()
            val channel: ByteReadChannel = response.body()

            val prefix = "data:"
            val terminate = "[DONE]"

            producer = flow {
                while (!channel.isClosedForRead) {
                    val line = channel.readUTF8Line()
                    line?.removePrefix(prefix)?.trim()?.let {
                        if (it.isNotBlank() && it != terminate) {
                            val completion = Json.decodeFromString<R>(it)
                            emit(completion)
                        }
                    }
                }
                currentCoroutineContext().cancel()
            }
        } catch (e: ClientRequestException) {
            error = OpenAiUtils.parseOpenAiClientRequestError(e.message)
        } finally {
            return Response(null, "", error, producer)
        }
    }

    private suspend inline fun <reified R : Any> makeRequest(fn: () -> HttpResponse): Response<R> {
        var result: R? = null
        var raw: String? = null
        var error: OpenAiClientRequestError? = null

        try {
            val response = fn()
            result = response.body()
            raw = response.bodyAsText()
        } catch (e: ClientRequestException) {
            error = OpenAiUtils.parseOpenAiClientRequestError(e.message)
        } finally {
            return Response(result, raw, error)
        }
    }

    private suspend inline fun <reified T : Any, reified R : Any> submitForm(
        resource: T,
        body: MultiPartFormDataSupported
    ): Response<R> {
        return makeRequest { client.post(resource) {
            contentType(ContentType.MultiPart.FormData)
            setBody(body.toMultiPartFormData()) }
        }
    }
    private suspend inline fun <reified T : Any, reified R : Any> post(
        resource: T,
        request: Any?
    ): Response<R> {
        return if (request is StreamingSupported && request.isStreamingRequest()) {
            makeStreamingRequest { client.post(resource) { setBody(request) } }
        } else {
            makeRequest { client.post(resource) { setBody(request) } }
        }
    }

    private suspend inline fun <reified T : Any, reified R : Any> get(resource: T): Response<R> {
        return if (resource is StreamingSupported && resource.isStreamingRequest()) {
            makeStreamingRequest { client.get(resource) }
        } else {
            makeRequest { client.get(resource) }
        }
    }

    private suspend inline fun <reified T : Any, reified R : Any> delete(resource: T): Response<R> {
        return makeRequest { client.delete(resource) }
    }
}