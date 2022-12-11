import com.henrymxu.openaikotlin.models.CreateFineTuneRequest
import com.henrymxu.openaikotlin.models.File
import com.henrymxu.openaikotlin.models.FineTune
import com.henrymxu.openaikotlin.models.FineTuneEvent
import com.henrymxu.openaikotlin.models.FineTuneEventsResult
import com.henrymxu.openaikotlin.models.FineTunesResult
import com.henrymxu.openaikotlin.models.HyperParams
import kotlinx.serialization.SerialName

@JsExport
@JsName("CreateFineTuneRequest")
external interface CreateFineTuneRequestWrapper {
    val trainingFile: String
    val validationFile: String?
    val model: String?
    val nEpochs: Int?
    val batchSize: Int?
    val learningRateMultiplier: Double?
    val promptLossWeight: Double?
    val computeClassificationMetrics: Boolean?
    val classificationNClasses: Int?
    val classificationPositiveClass: String?
    val classificationBetas: Array<Double>?
    val suffix: String?
}

fun CreateFineTuneRequestWrapper.unwrap() = CreateFineTuneRequest(
    trainingFile, validationFile, model, nEpochs,
    batchSize, learningRateMultiplier, promptLossWeight,
    computeClassificationMetrics, classificationNClasses,
    classificationPositiveClass, classificationBetas?.toList(), suffix
)

//@JsExport
//@JsName("CreateFineTuneRequest")
//data class CreateFineTuneRequestWrapper(
//    val trainingFile: String,
//    val validationFile: String? = null,
//    val model: String? = null,
//    val nEpochs: Int? = null,
//    val batchSize: Int? = null,
//    val learningRateMultiplier: Double? = null,
//    val promptLossWeight: Double? = null,
//    val computeClassificationMetrics: Boolean? = null,
//    val classificationNClasses: Int? = null,
//    val classificationPositiveClass: String? = null,
//    val classificationBetas: Array<Double>? = null,
//    val suffix: String? = null
//): RequestWrapper<CreateFineTuneRequest> {
//    @Suppress("NON_EXPORTABLE_TYPE")
//    override fun unwrap() = CreateFineTuneRequest(
//        trainingFile, validationFile, model, nEpochs,
//        batchSize, learningRateMultiplier, promptLossWeight,
//        computeClassificationMetrics, classificationNClasses,
//        classificationPositiveClass, classificationBetas?.toList(), suffix
//    )
//}

@JsExport
@JsName("FineTunesResult")
data class FineTunesResultWrapper(
    val data: Array<FineTuneWrapper>,
    val type: String
)

fun FineTunesResult.wrap(): FineTunesResultWrapper {
    return FineTunesResultWrapper(data.map { it.wrap() }.toTypedArray(), type)
}

@JsExport
@JsName("FineTuneEventsResult")
data class FineTuneEventsResultWrapper(
    @SerialName("object")
    val type: String,
    val data: Array<FineTuneEventWrapper>
)

fun FineTuneEventsResult.wrap(): FineTuneEventsResultWrapper {
    return FineTuneEventsResultWrapper(type, data.map { it.wrap() }.toTypedArray())
}

typealias FineTuneResultWrapper = FineTuneWrapper
@JsExport
@JsName("FineTune")
data class FineTuneWrapper(
    val id: String,
    val type: String,
    val model: String,
    val createdAt: Long,
    val events: Array<FineTuneEventWrapper>? = null,
    val fineTunedModel: String?,
    val hyperParams: HyperParams,
    val organizationId: String,
    val resultFiles: Array<File>,
    val status: String,
    val validationFiles: Array<File>,
    val trainingFiles: Array<File>,
    val updatedAt: Long,
)

fun FineTune.wrap(): FineTuneWrapper {
    return FineTuneWrapper(
        id, type, model, createdAt,
        events?.map { it.wrap() }?.toTypedArray(),
        fineTunedModel, hyperParams, organizationId,
        resultFiles.toTypedArray(), status, validationFiles.toTypedArray(),
        trainingFiles.toTypedArray(), updatedAt
    )
}

typealias FineTuneEventResultWrapper = FineTuneEventWrapper

@JsExport
@JsName("FineTuneEvent")
data class FineTuneEventWrapper(
    val type: String,
    val createdAt: Long,
    val level: String,
    val message: String
)

fun FineTuneEvent.wrap(): FineTuneEventWrapper {
    return FineTuneEventWrapper(type, createdAt, level, message)
}