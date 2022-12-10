package services

import ResponseWrapper
import CreateImageRequestWrapper
import EditImageRequestWrapper
import ImageResultWrapper
import VariateImageRequestWrapper
import kotlin.js.Promise

/**
 * @see com.henrymxu.openaikotlin.services.ImagesApi
 */
@JsExport
@JsName("ImagesApi")
interface ImagesApiWrapper {
    fun createImage(request: CreateImageRequestWrapper): Promise<ResponseWrapper<ImageResultWrapper>>
    fun editImage(request: EditImageRequestWrapper): Promise<ResponseWrapper<ImageResultWrapper>>
    fun variateImage(request: VariateImageRequestWrapper): Promise<ResponseWrapper<ImageResultWrapper>>
}
