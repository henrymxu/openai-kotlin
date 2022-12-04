package com.henryxu.openaikotlin.internal

import com.henryxu.openaikotlin.OpenAiApi
import com.henryxu.openaikotlin.Response
import com.henryxu.openaikotlin.models.*
import com.henryxu.openaikotlin.services.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.resources.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*

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

    override suspend fun listFineTuneEvents(
        fineTuneId: String,
        stream: Boolean?
    ): Response<FineTuneEventsResult> = get(FineTunesResource.FineTuneEventsResource(fineTuneId, stream = stream))

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

    private suspend inline fun <reified R : Any> makeRequest(fn: () -> HttpResponse): Response<R> {
        var result: R? = null
        var raw: String? = null
        var error: Error? = null

        try {
            val response = fn()
            result = response.body()
            raw = response.bodyAsText()
        } catch (e: Exception) {
            error = Error(e)
        } finally {
            return Response(result, raw, error)
        }
    }

    private suspend inline fun <reified T : Any, reified R : Any> submitForm(
        request: T,
        body: MultiPartFormDataSupported
    ): Response<R> {
        return makeRequest { client.post(request) {
            contentType(ContentType.MultiPart.FormData)
            setBody(body.toMultiPartFormData()) }
        }
    }
    private suspend inline fun <reified T : Any, reified R : Any> post(
        request: T,
        body: Any?
    ): Response<R> {
        return makeRequest { client.post(request) { setBody(body) } }
    }

    private suspend inline fun <reified T : Any, reified R : Any> get(request: T): Response<R> {
        return makeRequest { client.get(request) }
    }

    private suspend inline fun <reified T : Any, reified R : Any> delete(request: T): Response<R> {
        return makeRequest { client.delete(request) }
    }
}