package openaikotlin.services

import openaikotlin.ResponseWrapper
import openaikotlin.ModelResultWrapper
import openaikotlin.ModelsResultWrapper
import kotlin.js.Promise

/**
 * @see com.henryxu.openaikotlin.services.ModelsApi
 */
@JsExport
@JsName("ModelsApi")
interface ModelsApiWrapper {
    fun listModels(): Promise<ResponseWrapper<ModelsResultWrapper>>
    fun retrieveModel(modelId: String): Promise<ResponseWrapper<ModelResultWrapper>>
}
