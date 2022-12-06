package openaikotlin

import com.henryxu.openaikotlin.models.Choice
import com.henryxu.openaikotlin.models.CompletionResult
import com.henryxu.openaikotlin.models.CreateCompletionRequest
import com.henryxu.openaikotlin.models.Usage
import openaikotlin.internal.toMap
import kotlin.js.Json

@JsExport
@JsName("CreateCompletionRequest")
external interface CreateCompletionRequestWrapper {
    val model: String
    var prompt: String?
    var suffix: String?
    val maxTokens: Int?
    val temperature: Double?
    val topP: Double?
    val n: Int?
    val stream: Boolean?
    val logprobs: Int?
    val echo: Boolean?
    val stop: Array<String>?
    val presencePenalty: Double?
    val frequencyPenalty: Double?
    val bestOf: Int?
    val logitBias: Json?
    val user: String?
}

fun CreateCompletionRequestWrapper.unwrap() = CreateCompletionRequest(
    model, prompt, suffix, maxTokens, temperature, topP, n, stream,
    logprobs, echo, stop?.toList(), presencePenalty, frequencyPenalty,
    bestOf, logitBias?.toMap(), user
)

//@JsExport
//@JsName("CreateCompletionRequest")
//data class CreateCompletionRequestWrapper(
//    val model: String,
//    val prompt: String? = null,
//    val suffix: String? = null,
//    val maxTokens: Int? = null,
//    val temperature: Double? = null,
//    val topP: Double? = null,
//    val n: Int? = null,
//    val stream: Boolean? = null,
//    val logprobs: Int? = null,
//    val echo: Boolean? = null,
//    val stop: Array<String>? = null,
//    val presencePenalty: Double? = null,
//    val frequencyPenalty: Double? = null,
//    val bestOf: Int? = null,
//    val logitBias: Json? = null,
//    val user: String? = null,
//    val streamCallback: ((CompletionResultWrapper) -> Unit)? = null,
//): RequestWrapper<CreateCompletionRequest> {
//    @Suppress("NON_EXPORTABLE_TYPE")
//    override fun unwrap() = CreateCompletionRequest(
//        model, prompt, suffix, maxTokens, temperature,
//        topP, n, stream, logprobs, echo, stop?.toList(),
//        presencePenalty, frequencyPenalty, bestOf,
//        logitBias?.toMap(), user
//    )
//}

typealias CompletionResultWrapper = CompletionWrapper

@JsExport
@JsName("Completion")
data class CompletionWrapper(
    val id: String,
    val type: String,
    val created: Long,
    val model: String,
    val choices: Array<Choice>,
    val usage: Usage? = null
)

fun CompletionResult.wrap(): CompletionResultWrapper {
    return CompletionResultWrapper(id, type, created, model, choices.toTypedArray(), usage)
}
