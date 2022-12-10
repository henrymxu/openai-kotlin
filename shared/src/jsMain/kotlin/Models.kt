import com.henrymxu.openaikotlin.models.ModelResult
import com.henrymxu.openaikotlin.models.ModelsResult
import com.henrymxu.openaikotlin.models.Permission


@JsExport
@JsName("ModelsResult")
data class ModelsResultWrapper(
    val data: Array<ModelResultWrapper>,
    val type: String
)

fun ModelsResult.wrap(): ModelsResultWrapper {
    return ModelsResultWrapper(data.map { it.wrap() }.toTypedArray(), type)
}

typealias ModelResultWrapper = ModelWrapper

@JsExport
@JsName("Model")
data class ModelWrapper(
    @Suppress("NON_EXPORTABLE_TYPE")
    val created: Long,
    val id: String,
    val type: String,
    val owner: String,
    val parent: String?,
    val permissions: Array<Permission>,
    val root: String
)

fun ModelResult.wrap(): ModelResultWrapper {
    return ModelWrapper(created, id, type, owner, parent, permissions.toTypedArray(), root)
}
