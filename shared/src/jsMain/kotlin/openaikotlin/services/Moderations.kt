package openaikotlin.services

import openaikotlin.ResponseWrapper
import openaikotlin.CreateModerationRequestWrapper
import openaikotlin.ModerationResultWrapper
import kotlin.js.Promise

/**
 * @see com.henryxu.openaikotlin.services.ModerationsApi
 */
@JsExport
@JsName("ModerationsApi")
interface ModerationsApiWrapper {
    fun createModeration(request: CreateModerationRequestWrapper): Promise<ResponseWrapper<ModerationResultWrapper>>
}
