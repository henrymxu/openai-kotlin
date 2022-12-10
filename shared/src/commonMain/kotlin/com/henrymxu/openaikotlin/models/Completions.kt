package com.henrymxu.openaikotlin.models

import com.henrymxu.openaikotlin.internal.StreamingSupportedRequest
import kotlinx.serialization.SerialName

/**
 * The stream value is unused, to retrieve results as a stream use the following api
 * @see com.henrymxu.openaikotlin.services.CompletionsApi.streamCreateCompletion
 */
@kotlinx.serialization.Serializable
data class CreateCompletionRequest(
    val model: String,
    val prompt: String? = null,
    val suffix: String? = null,
    @SerialName("max_tokens")
    val maxTokens: Int? = null,
    val temperature: Double? = null,
    @SerialName("top_p")
    val topP: Double? = null,
    val n: Int? = null,
    val stream: Boolean? = null,
    val logprobs: Int? = null,
    val echo: Boolean? = null,
    val stop: List<String>? = null,
    @SerialName("presence_penalty")
    val presencePenalty: Double? = null,
    @SerialName("frequency_penalty")
    val frequencyPenalty: Double? = null,
    @SerialName("best_of")
    val bestOf: Int? = null,
    @SerialName("logit_bias")
    val logitBias: Map<String, Int>? = null,
    val user: String? = null
): StreamingSupportedRequest {
    override fun isStreamRequest(): Boolean {
        return stream == true
    }
}

typealias CompletionResult = Completion

@kotlinx.serialization.Serializable
data class Completion(
    val id: String,
    @SerialName("object")
    val type: String,
    val created: Long,
    val model: String,
    val choices: List<Choice>,
    val usage: Usage? = null
)



