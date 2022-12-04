package com.henryxu.openaikotlin.models

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class ModerationResult(
    val id: String,
    val model: String,
    val results: List<ModerationData>,
)

// TODO: Merge categories and category_scores in custom serializer?
@kotlinx.serialization.Serializable
data class ModerationData(
    val categories: ModerationCategories,
    @SerialName("category_scores")
    val categoryScores: ModerationCategoryScores,
    val flagged: Boolean
)

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