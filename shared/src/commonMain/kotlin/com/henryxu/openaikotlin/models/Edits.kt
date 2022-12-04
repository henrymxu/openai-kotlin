package com.henryxu.openaikotlin.models

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class CreateEditRequest(
    val model: String,
    val input: String? = null,
    val instruction: String,
    val n: Int? = null,
    val temperature: Double? = null,
    @SerialName("top_p")
    val topP: Double? = null
)

typealias EditResult = Edit

@kotlinx.serialization.Serializable
data class Edit(
    @SerialName("object")
    val type: String,
    val created: Long,
    val choices: List<Choice>,
    val usage: Usage
)
