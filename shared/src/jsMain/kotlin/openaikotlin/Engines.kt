package openaikotlin

import com.henryxu.openaikotlin.models.EngineResult
import com.henryxu.openaikotlin.models.EnginesResult

@JsExport
@JsName("EnginesResult")
data class EnginesResultWrapper(
    val data: Array<EngineResultWrapper>,
    val type: String
)

fun EnginesResult.wrap(): EnginesResultWrapper {
    return EnginesResultWrapper(data.map { it.wrap() }.toTypedArray(), type)
}

typealias EngineResultWrapper = EngineWrapper

@JsExport
@JsName("Engine")
data class EngineWrapper(
    val id: String,
    val type: String,
    val owner: String,
    val ready: Boolean
)

fun EngineResult.wrap(): EngineResultWrapper {
    return EngineWrapper(id, type, owner, ready)
}
