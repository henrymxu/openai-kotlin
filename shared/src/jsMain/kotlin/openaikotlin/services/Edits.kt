package openaikotlin.services

import openaikotlin.ResponseWrapper
import openaikotlin.CreateEditRequestWrapper
import openaikotlin.EditResultWrapper
import kotlin.js.Promise

/**
 * @see com.henrymxu.openaikotlin.services.EditsApi
 */
@JsExport
@JsName("EditsApi")
interface EditsApiWrapper {
    fun createEdit(request: CreateEditRequestWrapper): Promise<ResponseWrapper<EditResultWrapper>>
}
