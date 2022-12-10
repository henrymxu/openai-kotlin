package openaikotlin.services

import openaikotlin.ResponseWrapper
import openaikotlin.CreateImageRequestWrapper
import openaikotlin.EditImageRequestWrapper
import openaikotlin.ImageResultWrapper
import openaikotlin.VariateImageRequestWrapper
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
