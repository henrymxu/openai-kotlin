package services

import ResponseWrapper
import CreateModerationRequestWrapper
import ModerationResultWrapper
import kotlin.js.Promise

/**
 * @see com.henrymxu.openaikotlin.services.ModerationsApi
 */
@JsExport
@JsName("ModerationsApi")
interface ModerationsApiWrapper {
    fun createModeration(request: CreateModerationRequestWrapper): Promise<ResponseWrapper<ModerationResultWrapper>>
}
