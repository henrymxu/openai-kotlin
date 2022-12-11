package services

import ResponseWrapper
import CreateEmbeddingsRequestWrapper
import EmbeddingResultWrapper
import kotlin.js.Promise

/**
 * @see com.henrymxu.openaikotlin.services.EmbeddingsApi
 */
@JsExport
@JsName("EmbeddingsApi")
interface EmbeddingsApiWrapper {
    fun createEmbeddings(request: CreateEmbeddingsRequestWrapper): Promise<ResponseWrapper<EmbeddingResultWrapper>>
}
