package com.henrymxu.openaikotlin.internal

import com.henrymxu.openaikotlin.OpenAiApi
import com.henrymxu.openaikotlin.Response
import com.henrymxu.openaikotlin.internal.resources.CompletionsResource
import com.henrymxu.openaikotlin.internal.resources.EditsResource
import com.henrymxu.openaikotlin.internal.resources.EmbeddingsResource
import com.henrymxu.openaikotlin.internal.resources.EnginesResource
import com.henrymxu.openaikotlin.internal.resources.FilesResource
import com.henrymxu.openaikotlin.internal.resources.FineTunesResource
import com.henrymxu.openaikotlin.internal.resources.ImagesResource
import com.henrymxu.openaikotlin.internal.resources.ModelsResource
import com.henrymxu.openaikotlin.internal.resources.ModerationsResource
import com.henrymxu.openaikotlin.models.CompletionResult
import com.henrymxu.openaikotlin.models.CreateCompletionRequest
import com.henrymxu.openaikotlin.models.CreateEditRequest
import com.henrymxu.openaikotlin.models.CreateEmbeddingsRequest
import com.henrymxu.openaikotlin.models.CreateFineTuneRequest
import com.henrymxu.openaikotlin.models.CreateImageRequest
import com.henrymxu.openaikotlin.models.CreateModerationRequest
import com.henrymxu.openaikotlin.models.EditImageRequest
import com.henrymxu.openaikotlin.models.EditResult
import com.henrymxu.openaikotlin.models.EmbeddingResult
import com.henrymxu.openaikotlin.models.EngineResult
import com.henrymxu.openaikotlin.models.EnginesResult
import com.henrymxu.openaikotlin.models.EntityDeleteResult
import com.henrymxu.openaikotlin.models.FileResult
import com.henrymxu.openaikotlin.models.FilesResult
import com.henrymxu.openaikotlin.models.FineTuneEvent
import com.henrymxu.openaikotlin.models.FineTuneEventsResult
import com.henrymxu.openaikotlin.models.FineTuneResult
import com.henrymxu.openaikotlin.models.FineTunesResult
import com.henrymxu.openaikotlin.models.ImageResult
import com.henrymxu.openaikotlin.models.ModelResult
import com.henrymxu.openaikotlin.models.ModelsResult
import com.henrymxu.openaikotlin.models.ModerationResult
import com.henrymxu.openaikotlin.models.OpenAiClientRequestError
import com.henrymxu.openaikotlin.models.UploadFileRequest
import com.henrymxu.openaikotlin.models.VariateImageRequest
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.resources.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.JsonConvertException
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

    override suspend fun streamCreateCompletion(request: CreateCompletionRequest): Flow<Response<CompletionResult>> {
        return postStream(CompletionsResource(), request)
    }

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
        submitForm(FilesResource(), request)

    override suspend fun deleteFile(fileId: String): Response<EntityDeleteResult> =
        delete(FilesResource.FileResource(fileId))

    override suspend fun createFineTune(request: CreateFineTuneRequest): Response<FineTuneResult> =
        post(FineTunesResource(), request)

    override suspend fun listFineTunes(): Response<FineTunesResult> =
        get(FineTunesResource())

    override suspend fun retrieveFineTune(fineTuneId: String): Response<FineTuneResult> =
        get(FineTunesResource.FineTuneResource(fineTuneId))

    override suspend fun cancelFineTune(fineTuneId: String): Response<FineTuneResult> =
        post(FineTunesResource.FineTuneCancelResource(fineTuneId), null)

    override suspend fun listFineTuneEvents(fineTuneId: String): Response<FineTuneEventsResult> =
        get(FineTunesResource.FineTuneEventsResource(fineTuneId, stream = false))

    override suspend fun streamFineTuneEvents(fineTuneId: String): Flow<Response<FineTuneEvent>> =
        getStream(FineTunesResource.FineTuneEventsResource(fineTuneId, stream = true))

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

    private suspend inline fun <reified R: Any> makeStreamingRequest(fn: () -> HttpResponse): Flow<Response<R>> {
        try {
            val response = fn()
            val channel: ByteReadChannel = response.body()

            val prefix = "data:"
            val terminate = "[DONE]"

            return flow {
                while (!channel.isClosedForRead) {
                    try {
                        val line = channel.readUTF8Line()
                        line?.removePrefix(prefix)?.trim()?.let {
                            if (it.isNotBlank() && it != terminate) {
                                val completion = Json.decodeFromString<R>(it)
                                emit(Response(completion, line, null))
                            }
                        }
                    } catch (e: ClientRequestException) {
                        emit(Response(null, null, OpenAiUtils.parseOpenAiClientRequestError(e)))
                    }
                }
            }
        } catch (e: ClientRequestException) {
            return flow { emit(Response(null, null, OpenAiUtils.parseOpenAiClientRequestError(e)))}
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
            error = OpenAiUtils.parseOpenAiClientRequestError(e)
        } catch (e: JsonConvertException) {
            error = OpenAiClientRequestError(
                e.message ?: "", "json", "", ""
            )
        } finally {
            return Response(result, raw, error)
        }
    }

    private suspend inline fun <reified T : Any, reified R : Any> submitForm(
        resource: T,
        body: MultiPartFormDataRequest
    ): Response<R> {
        return makeRequest { client.post(resource) {
            contentType(ContentType.MultiPart.FormData)
            setBody(body.toMultiPartFormData()) }
        }
    }

    private suspend inline fun <reified T : Any, reified R : Any> post(resource: T, request: Any?): Response<R> {
        return makeRequest { client.post(resource) { setBody(request) } }
    }

    private suspend inline fun <reified T : Any, reified R : Any> postStream(resource: T, request: Any?): Flow<Response<R>> {
        return makeStreamingRequest { client.post(resource) { setBody(request) } }
    }

    private suspend inline fun <reified T : Any, reified R : Any> get(resource: T): Response<R> {
        return makeRequest { client.get(resource) }
    }

    private suspend inline fun <reified T : Any, reified R : Any> getStream(resource: T): Flow<Response<R>> {
        return makeStreamingRequest { client.get(resource) }
    }

    private suspend inline fun <reified T : Any, reified R : Any> delete(resource: T): Response<R> {
        return makeRequest { client.delete(resource) }
    }
}