package services

import ResponseWrapper
import ModelResultWrapper
import ModelsResultWrapper
import kotlin.js.Promise

/**
 * @see com.henrymxu.openaikotlin.services.ModelsApi
 */
@JsExport
@JsName("ModelsApi")
interface ModelsApiWrapper {
    fun listModels(): Promise<ResponseWrapper<ModelsResultWrapper>>
    fun retrieveModel(modelId: String): Promise<ResponseWrapper<ModelResultWrapper>>
}
