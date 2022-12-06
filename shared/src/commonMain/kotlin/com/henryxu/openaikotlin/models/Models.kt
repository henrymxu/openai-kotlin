package com.henryxu.openaikotlin.models

import kotlinx.serialization.SerialName
import kotlin.js.JsExport

@kotlinx.serialization.Serializable
data class ModelsResult(
    val data: List<Model>,
    @SerialName("object")
    val type: String
)

typealias ModelResult = Model

@kotlinx.serialization.Serializable
data class Model(
    val created: Long,
    val id: String,
    @SerialName("object")
    val type: String,
    @SerialName("owned_by")
    val owner: String,
    val parent: String?,
    @SerialName("permission")
    val permissions: List<Permission>,
    val root: String
)

@JsExport
@kotlinx.serialization.Serializable
data class Permission(
    @SerialName("allow_create_engine")
    val allowCreateEngine: Boolean,
    @SerialName("allow_fine_tuning")
    val allowFineTuing: Boolean,
    @SerialName("allow_logprobs")
    val allowLogprobs: Boolean,
    @SerialName("allow_sampling")
    val allowSampling: Boolean,
    @SerialName("allow_search_indices")
    val allowSearchIndices: Boolean,
    @SerialName("allow_view")
    val allowView: Boolean,
    val created: Long,
    val group: String?,
    val id: String,
    @SerialName("is_blocking")
    val isBlocking: Boolean,
    @SerialName("object")
    val type: String,
    val organization: String
)
