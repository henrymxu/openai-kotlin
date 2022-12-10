import com.henrymxu.openaikotlin.OpenAiClientBuilder
import com.henrymxu.openaikotlin.internal.ConcreteOpenAiClient
import com.henrymxu.openaikotlin.models.EntityDeleteResult
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.promise
import kotlin.js.Promise

@JsExport
external interface OpenAiClientConfig {
    val apiKey: String
}

@JsExport
@JsName("OpenAiClient")
@OptIn(DelicateCoroutinesApi::class)
class OpenAiClientWrapper(config: OpenAiClientConfig): OpenAiApiWrapper {
    private val client = ConcreteOpenAiClient(OpenAiClientBuilder(config.apiKey))
    private val api = client.api

    override fun createCompletion(request: CreateCompletionRequestWrapper): Promise<ResponseWrapper<CompletionResultWrapper>> {
        return GlobalScope.promise {
            return@promise api.createCompletion(request.unwrap()).wrapResponse {
                it?.wrap()
            }
        }
    }

    override fun createCompletionStream(
        request: CreateCompletionRequestWrapper,
        handler: (ResponseWrapper<CompletionResultWrapper>) -> Unit
    ) {
        GlobalScope.launch {
            val stream = api.streamCreateCompletion(request.unwrap())

            stream.collect { result ->
                handler(result.wrapResponse { it?.wrap() })
            }
        }
    }

    override fun createEdit(request: CreateEditRequestWrapper): Promise<ResponseWrapper<EditResultWrapper>> {
        return GlobalScope.promise {
            return@promise api.createEdit(request.unwrap()).wrapResponse {
                it?.wrap()
            }
        }
    }

    override fun createImage(request: CreateImageRequestWrapper): Promise<ResponseWrapper<ImageResultWrapper>> {
        return GlobalScope.promise {
            return@promise api.createImage(request.unwrap()).wrapResponse {
                it?.wrap()
            }
        }
    }

    override fun editImage(request: EditImageRequestWrapper): Promise<ResponseWrapper<ImageResultWrapper>> {
        return GlobalScope.promise {
            return@promise api.editImage(request.unwrap()).wrapResponse {
                it?.wrap()
            }
        }
    }

    override fun variateImage(request: VariateImageRequestWrapper): Promise<ResponseWrapper<ImageResultWrapper>> {
        return GlobalScope.promise {
            return@promise api.variateImage(request.unwrap()).wrapResponse {
                it?.wrap()
            }
        }
    }

    override fun createEmbeddings(request: CreateEmbeddingsRequestWrapper): Promise<ResponseWrapper<EmbeddingResultWrapper>> {
        return GlobalScope.promise {
            return@promise api.createEmbeddings(request.unwrap()).wrapResponse {
                it?.wrap()
            }
        }
    }

    override fun listFiles(): Promise<ResponseWrapper<FilesResultWrapper>> {
        return GlobalScope.promise {
            return@promise api.listFiles().wrapResponse {
                it?.wrap()
            }
        }
    }

    override fun retrieveFile(fileId: String): Promise<ResponseWrapper<FileResultWrapper>> {
        return GlobalScope.promise {
            return@promise api.retrieveFile(fileId).wrapResponse {
                it?.wrap()
            }
        }
    }

    override fun retrieveFileContent(fileId: String): Promise<ResponseWrapper<Any>> {
        return GlobalScope.promise {
            return@promise api.retrieveFileContent(fileId).wrapResponse { it }
        }
    }

    override fun uploadFile(request: UploadFileRequestWrapper): Promise<ResponseWrapper<FileResultWrapper>> {
        return GlobalScope.promise {
            return@promise api.uploadFile(request.unwrap()).wrapResponse {
                it?.wrap()
            }
        }
    }

    override fun deleteFile(fileId: String): Promise<ResponseWrapper<EntityDeleteResult>> {
        return GlobalScope.promise {
            return@promise api.deleteFile(fileId).wrapResponse { it }
        }
    }

    override fun createFineTune(request: CreateFineTuneRequestWrapper): Promise<ResponseWrapper<FineTuneResultWrapper>> {
        return GlobalScope.promise {
            return@promise api.createFineTune(request.unwrap()).wrapResponse {
                it?.wrap()
            }
        }
    }

    override fun listFineTunes(): Promise<ResponseWrapper<FineTunesResultWrapper>> {
        return GlobalScope.promise {
            return@promise api.listFineTunes().wrapResponse {
                it?.wrap()
            }
        }
    }

    override fun retrieveFineTune(fineTuneId: String): Promise<ResponseWrapper<FineTuneResultWrapper>> {
        return GlobalScope.promise {
            return@promise api.retrieveFineTune(fineTuneId).wrapResponse {
                it?.wrap()
            }
        }
    }

    override fun cancelFineTune(fineTuneId: String): Promise<ResponseWrapper<FineTuneResultWrapper>> {
        return GlobalScope.promise {
            return@promise api.cancelFineTune(fineTuneId).wrapResponse {
                it?.wrap()
            }
        }
    }

    override fun listFineTuneEvents(fineTuneId: String): Promise<ResponseWrapper<FineTuneEventsResultWrapper>> {
        return GlobalScope.promise {
            return@promise api.listFineTuneEvents(fineTuneId).wrapResponse {
                it?.wrap()
            }
        }
    }

    override fun streamFineTuneEvents(
        fineTuneId: String,
        handler: (ResponseWrapper<FineTuneEventResultWrapper>) -> Unit
    ) {
        GlobalScope.launch {
            val stream = api.streamFineTuneEvents(fineTuneId)

            stream.collect { result ->
                handler(result.wrapResponse { it?.wrap() })
            }
        }
    }

    override fun deleteFineTuneModel(modelId: String): Promise<ResponseWrapper<EntityDeleteResult>> {
        return GlobalScope.promise {
            return@promise api.deleteFineTuneModel(modelId).wrapResponse { it }
        }
    }

    override fun listModels(): Promise<ResponseWrapper<ModelsResultWrapper>> {
        return GlobalScope.promise {
            return@promise api.listModels().wrapResponse {
                it?.wrap()
            }
        }
    }

    override fun retrieveModel(modelId: String): Promise<ResponseWrapper<ModelResultWrapper>> {
        return GlobalScope.promise {
            return@promise api.retrieveModel(modelId).wrapResponse {
                it?.wrap()
            }
        }
    }

    override fun createModeration(request: CreateModerationRequestWrapper): Promise<ResponseWrapper<ModerationResultWrapper>> {
        return GlobalScope.promise {
            return@promise api.createModeration(request.unwrap()).wrapResponse {
                it?.wrap()
            }
        }
    }

    override fun listEngines(): Promise<ResponseWrapper<EnginesResultWrapper>> {
        return GlobalScope.promise {
            return@promise api.listEngines().wrapResponse {
                it?.wrap()
            }
        }
    }

    override fun retrieveEngine(engineId: String): Promise<ResponseWrapper<EngineResultWrapper>> {
        return GlobalScope.promise {
            return@promise api.retrieveEngine(engineId).wrapResponse {
                it?.wrap()
            }
        }
    }
}
