import com.henrymxu.openaikotlin.models.Choice
import com.henrymxu.openaikotlin.models.CreateEditRequest
import com.henrymxu.openaikotlin.models.EditResult
import com.henrymxu.openaikotlin.models.Usage

@JsExport
@JsName("CreateEditRequest")
external interface CreateEditRequestWrapper {
    val model: String
    val input: String?
    val instruction: String
    val n: Int?
    val temperature: Double?
    val topP: Double?
}

fun CreateEditRequestWrapper.unwrap() = CreateEditRequest(model, input, instruction, n, temperature, topP)

//@JsExport
//@JsName("CreateEditRequest")
//data class CreateEditRequestWrapper(
//    val model: String,
//    val input: String? = null,
//    val instruction: String,
//    val n: Int? = null,
//    val temperature: Double? = null,
//    val topP: Double? = null
//): RequestWrapper<CreateEditRequest> {
//    @Suppress("NON_EXPORTABLE_TYPE")
//    override fun unwrap() = CreateEditRequest(model, input, instruction, n, temperature, topP)
//}

typealias EditResultWrapper = EditWrapper

@JsExport
@JsName("Edit")
data class EditWrapper(
    val type: String,
    val created: Long,
    val choices: Array<Choice>,
    val usage: Usage
)

fun EditResult.wrap(): EditResultWrapper {
    return EditResultWrapper(type, created, choices.toTypedArray(), usage)
}
