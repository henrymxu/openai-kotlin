package com.henryxu.openaikotlin.models

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class CreateFineTuneRequest(
    @SerialName("training_file")
    val trainingFile: String,
    @SerialName("validation_file")
    val validationFile: String? = null,
    val model: String? = null,
    @SerialName("n_epochs")
    val nEpochs: Int? = null,
    @SerialName("batch_size")
    val batchSize: Int? = null,
    @SerialName("learning_rate_multiplier")
    val learningRateMultiplier: Double? = null,
    @SerialName("prompt_loss_weight")
    val promptLossWeight: Double? = null,
    @SerialName("compute_classification_metrics")
    val computeClassificationMetrics: Boolean? = null,
    @SerialName("classification_n_classes")
    val classificationNClasses: Int? = null,
    @SerialName("classification_positive_class")
    val classificationPositiveClass: String? = null,
    @SerialName("classification_betas")
    val classificationBetas: List<Double>? = null,
    val suffix: String? = null
)

@kotlinx.serialization.Serializable
data class FineTunesResult(
    val data: List<FineTune>,
    @SerialName("object")
    val type: String
)

typealias FineTuneResult = FineTune

@kotlinx.serialization.Serializable
data class FineTuneEventsResult(
    @SerialName("object")
    val type: String,
    val data: List<FineTuneEvent>
)

@kotlinx.serialization.Serializable
data class FineTune(
    val id: String,
    @SerialName("object")
    val type: String,
    val model: String,
    @SerialName("created_at")
    val createdAt: Long,
    val events: List<FineTuneEvent>? = null,
    @SerialName("fine_tuned_model")
    val fineTunedModel: String?,
    @SerialName("hyperparams")
    val hyperParams: HyperParams,
    @SerialName("organization_id")
    val organizationId: String,
    @SerialName("result_files")
    val resultFiles: List<File>,
    val status: String,
    @SerialName("validation_files")
    val validationFiles: List<File>,
    @SerialName("training_files")
    val trainingFiles: List<File>,
    @SerialName("updated_at")
    val updatedAt: Long,
)

@kotlinx.serialization.Serializable
data class FineTuneEvent(
    @SerialName("object")
    val type: String,
    @SerialName("created_at")
    val createdAt: Long,
    val level: String,
    val message: String
)

@kotlinx.serialization.Serializable
data class HyperParams(
    @SerialName("n_epochs")
    val nEpochs: Int? = null,
    @SerialName("batch_size")
    val batchSize: Int? = null,
    @SerialName("learning_rate_multiplier")
    val learningRateMultiplier: Double? = null,
    @SerialName("prompt_loss_weight")
    val promptLossWeight: Double? = null,
)