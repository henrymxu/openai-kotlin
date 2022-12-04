package com.henryxu.openaikotlin.models

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class EnginesResult(
    val data: List<Engine>,
    @SerialName("object")
    val type: String
)

typealias EngineResult = Engine

@kotlinx.serialization.Serializable
data class Engine(
    val id: String,
    @SerialName("object")
    val type: String,
    val owner: String,
    val ready: Boolean
)