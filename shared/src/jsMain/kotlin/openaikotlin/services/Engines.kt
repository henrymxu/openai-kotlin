package openaikotlin.services

import openaikotlin.ResponseWrapper
import openaikotlin.EngineResultWrapper
import openaikotlin.EnginesResultWrapper
import kotlin.js.Promise

/**
 * @see com.henrymxu.openaikotlin.services.EnginesApi
 */
@JsExport
@JsName("EnginesApi")
@Deprecated(
    "Please use their replacement, Engines, instead. ",
    ReplaceWith("ModelsApi")
)
interface EnginesApiWrapper {
    fun listEngines(): Promise<ResponseWrapper<EnginesResultWrapper>>
    fun retrieveEngine(engineId: String): Promise<ResponseWrapper<EngineResultWrapper>>
}
