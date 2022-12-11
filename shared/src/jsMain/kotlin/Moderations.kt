import com.henrymxu.openaikotlin.models.CreateModerationRequest
import com.henrymxu.openaikotlin.models.ModerationData
import com.henrymxu.openaikotlin.models.ModerationModel
import com.henrymxu.openaikotlin.models.ModerationResult

@JsExport
@JsName("CreateModerationRequest")
external interface CreateModerationRequestWrapper {
    val input: String
    val model: ModerationModel?
}

fun CreateModerationRequestWrapper.unwrap() = CreateModerationRequest(input, model)

//@JsExport
//@JsName("CreateModerationRequest")
//data class CreateModerationRequestWrapper(
//    val input: String,
//    val model: ModerationModel? = null
//): RequestWrapper<CreateModerationRequest> {
//    @Suppress("NON_EXPORTABLE_TYPE")
//    override fun unwrap() = CreateModerationRequest(input, model)
//}

@JsExport
@JsName("ModerationResult")
data class ModerationResultWrapper(
    val id: String,
    val model: String,
    val results: Array<ModerationData>
)

fun ModerationResult.wrap(): ModerationResultWrapper {
    return ModerationResultWrapper(id, model, results.toTypedArray())
}