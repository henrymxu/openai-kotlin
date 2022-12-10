package com.henrymxu.openaikotlin.models

import kotlinx.serialization.SerialName
import kotlin.js.JsExport

@JsExport
@kotlinx.serialization.Serializable
data class OpenAiClientRequestError(
    val message: String,
    val type: String,
    val param: String?,
    val code: String?
)

@JsExport
@kotlinx.serialization.Serializable
data class Usage(
    @SerialName("prompt_tokens")
    val promptTokens: Int,
    @SerialName("completion_tokens")
    val completionTokens: Int? = null,
    @SerialName("total_tokens")
    val totalTokens: Int
)

@JsExport
@kotlinx.serialization.Serializable
data class Choice(
    val text: String,
    val index: Int,
    val logprobs: Double? = null,
    @SerialName("finish_reason")
    val finishReason: String? = null
)

@JsExport
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

@JsExport
@kotlinx.serialization.Serializable
data class EntityDeleteResult(
    val id: String,
    @SerialName("object")
    val type: String,
    val deleted: Boolean
)