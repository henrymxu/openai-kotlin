package com.henrymxu.openaikotlin.models

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class CreateEmbeddingsRequest(
    val model: String,
    val input: String,
    val user: String? = null,
)

@kotlinx.serialization.Serializable
data class EmbeddingResult(
    @SerialName("object")
    val type: String,
    val data: List<EmbeddingData>,
    val usage: Usage
)

@kotlinx.serialization.Serializable
data class EmbeddingData(
    @SerialName("object")
    val type: String,
    val embedding: List<Float>,
    val index: Int
)

