package services

import ResponseWrapper
import CreateEditRequestWrapper
import EditResultWrapper
import kotlin.js.Promise

/**
 * @see com.henrymxu.openaikotlin.services.EditsApi
 */
@JsExport
@JsName("EditsApi")
interface EditsApiWrapper {
    fun createEdit(request: CreateEditRequestWrapper): Promise<ResponseWrapper<EditResultWrapper>>
}
