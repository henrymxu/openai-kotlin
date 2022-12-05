package com.henryxu.openaikotlin.models

import io.ktor.client.request.forms.*
import kotlinx.serialization.SerialName

interface MultiPartFormDataSupported {
    fun toMultiPartFormData(): MultiPartFormDataContent
}

interface StreamingSupported {
    fun isStreamingRequest(): Boolean
}

@kotlinx.serialization.Serializable
data class OpenAiClientRequestError(
    val message: String,
    val type: String,
    val param: String?,
    val code: Int?
)

@kotlinx.serialization.Serializable
data class Usage(
    @SerialName("prompt_tokens")
    val promptTokens: Int,
    @SerialName("completion_tokens")
    val completionTokens: Int? = null,
    @SerialName("total_tokens")
    val totalTokens: Int
)

@kotlinx.serialization.Serializable
data class Choice(
    val text: String,
    val index: Int,
    val logprobs: Double? = null,
    @SerialName("finish_reason")
    val finishReason: String? = null
)

@kotlinx.serialization.Serializable
data class File(
    val id: String,
    @SerialName("object")
    val type: String,
    val bytes: Long,
    @SerialName("created_at")
    val createdAt: Long,
    @SerialName("filename")
    val fileName: String,
    val purpose: String
)

@kotlinx.serialization.Serializable
data class EntityDeleteResult(
    val id: String,
    @SerialName("object")
    val type: String,
    val deleted: Boolean
)