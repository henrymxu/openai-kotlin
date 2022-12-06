package openaikotlin.services

import openaikotlin.ResponseWrapper
import openaikotlin.CreateEmbeddingsRequestWrapper
import openaikotlin.EmbeddingResultWrapper
import kotlin.js.Promise

/**
 * @see com.henryxu.openaikotlin.services.EmbeddingsApi
 */
@JsExport
@JsName("EmbeddingsApi")
interface EmbeddingsApiWrapper {
    fun createEmbeddings(request: CreateEmbeddingsRequestWrapper): Promise<ResponseWrapper<EmbeddingResultWrapper>>
}
