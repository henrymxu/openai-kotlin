package openaikotlin

import com.henrymxu.openaikotlin.models.CreateImageRequest
import com.henrymxu.openaikotlin.models.EditImageRequest
import com.henrymxu.openaikotlin.models.ImageData
import com.henrymxu.openaikotlin.models.ImageFormat
import com.henrymxu.openaikotlin.models.ImageResult
import com.henrymxu.openaikotlin.models.ImageSize
import com.henrymxu.openaikotlin.models.VariateImageRequest

@JsExport
@JsName("CreateImageRequest")
external interface CreateImageRequestWrapper {
    val prompt: String
    val n: Int?
    val size: ImageSize?
    val responseFormat: ImageFormat?
    val user: String?
}

fun CreateImageRequestWrapper.unwrap() = CreateImageRequest(prompt, n, size, responseFormat, user)

//@JsExport
//@JsName("CreateImageRequest")
//data class CreateImageRequestWrapper(
//    val prompt: String,
//    val n: Int? = null,
//    val size: ImageSize? = null,
//    val responseFormat: ImageFormat? = null,
//    val user: String? = null
//): RequestWrapper<CreateImageRequest> {
//    @Suppress("NON_EXPORTABLE_TYPE")
//    override fun unwrap() = CreateImageRequest(prompt, n, size, responseFormat, user)
//}

@JsExport
@JsName("EditImageRequest")
external interface EditImageRequestWrapper {
    val image: ByteArray
    val mask: ByteArray?
    val prompt: String
    val n: Int?
    val size: ImageSize?
    val responseFormat: ImageFormat?
    val user: String?
}

fun EditImageRequestWrapper.unwrap() = EditImageRequest(image, mask, prompt, n, size, responseFormat, user)

//@JsExport
//@JsName("EditImageRequest")
//data class EditImageRequestWrapper(
//    val image: ByteArray,
//    val mask: ByteArray? = null,
//    val prompt: String,
//    val n: Int? = null,
//    val size: ImageSize? = null,
//    val responseFormat: ImageFormat? = null,
//    val user: String? = null
//): RequestWrapper<EditImageRequest> {
//    @Suppress("NON_EXPORTABLE_TYPE")
//    override fun unwrap() = EditImageRequest(image, mask, prompt, n, size, responseFormat, user)
//}

@JsExport
@JsName("VariateImageRequest")
external interface VariateImageRequestWrapper {
    val image: ByteArray
    val n: Int?
    val size: ImageSize?
    val responseFormat: ImageFormat?
    val user: String?
}

fun VariateImageRequestWrapper.unwrap() = VariateImageRequest(image, n, size, responseFormat, user)

//@JsExport
//@JsName("VariateImageRequest")
//data class VariateImageRequestWrapper(
//    val image: ByteArray,
//    val n: Int? = null,
//    val size: ImageSize? = null,
//    val responseFormat: ImageFormat? = null,
//    val user: String? = null
//): RequestWrapper<VariateImageRequest> {
//    @Suppress("NON_EXPORTABLE_TYPE")
//    override fun unwrap() = VariateImageRequest(image, n, size, responseFormat, user)
//}

@JsExport
@JsName("ImageResult")
data class ImageResultWrapper(
    @Suppress("NON_EXPORTABLE_TYPE")
    val created: Long,
    val data: Array<ImageData>
)

fun ImageResult.wrap(): ImageResultWrapper {
    return ImageResultWrapper(created, data.toTypedArray())
}
