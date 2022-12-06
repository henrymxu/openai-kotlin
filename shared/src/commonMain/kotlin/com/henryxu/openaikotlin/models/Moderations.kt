package com.henryxu.openaikotlin.models

import kotlinx.serialization.SerialName
import kotlin.js.JsExport

@kotlinx.serialization.Serializable
data class CreateModerationRequest(
    val input: String,
    val model: ModerationModel? = null
)

@kotlinx.serialization.Serializable
data class ModerationResult(
    val id: String,
    val model: String,
    val results: List<ModerationData>,
)

// TODO: Merge categories and category_scores in custom serializer?
@JsExport
@kotlinx.serialization.Serializable
data class ModerationData(
    val categories: ModerationCategories,
    @SerialName("category_scores")
    val categoryScores: ModerationCategoryScores,
    val flagged: Boolean
)

@JsExport
@kotlinx.serialization.Serializable
data class ModerationCategories(
    val hate: Boolean,
    @SerialName("hate/threatening")
    val hateOrThreatening: Boolean,
    @SerialName("self-harm")
    val selfHarm: Boolean,
    val sexual: Boolean,
    @SerialName("sexual/minors")
    val sexualOrMinors: Boolean,
    val violence: Boolean,
    @SerialName("violence/graphic")
    val violenceOrGraphic: Boolean
)

@JsExport
@kotlinx.serialization.Serializable
data class ModerationCategoryScores(
    val hate: Double,
    @SerialName("hate/threatening")
    val hateOrThreatening: Double,
    @SerialName("self-harm")
    val selfHarm: Double,
    val sexual: Double,
    @SerialName("sexual/minors")
    val sexualOrMinors: Double,
    val violence: Double,
    @SerialName("violence/graphic")
    val violenceOrGraphic: Double
)

@JsExport
@kotlinx.serialization.Serializable
enum class ModerationModel {
    @SerialName("text-moderation-stable")
    STABLE,
    @SerialName("text-moderation-latest")
    LATEST
}