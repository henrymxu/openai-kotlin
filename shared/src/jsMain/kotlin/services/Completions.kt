package services

import ResponseWrapper
import CompletionResultWrapper
import CreateCompletionRequestWrapper
import kotlin.js.Promise

/**
 * @see com.henrymxu.openaikotlin.services.CompletionsApi
 */
@JsExport
@JsName("CompletionsApi")
interface CompletionsApiWrapper {
    fun createCompletion(request: CreateCompletionRequestWrapper): Promise<ResponseWrapper<CompletionResultWrapper>>
    fun createCompletionStream(request: CreateCompletionRequestWrapper, handler: (ResponseWrapper<CompletionResultWrapper>) -> Unit)
}
