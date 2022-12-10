package com.henrymxu.openaikotlin.models

import com.henrymxu.openaikotlin.internal.MultiPartFormDataRequest
import com.henrymxu.openaikotlin.models.Utils.appendIfNotNull
import com.henrymxu.openaikotlin.models.Utils.appendImage
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import kotlinx.serialization.SerialName
import kotlin.js.JsExport

@kotlinx.serialization.Serializable
data class CreateImageRequest(
    val prompt: String,
    val n: Int? = null,
    val size: ImageSize? = null,
    @SerialName("response_format")
    val responseFormat: ImageFormat? = null,
    val user: String? = null
)

@kotlinx.serialization.Serializable
data class EditImageRequest(
    val image: ByteArray,
    val mask: ByteArray? = null,
    val prompt: String,
    val n: Int? = null,
    val size: ImageSize? = null,
    @SerialName("response_format")
    val responseFormat: ImageFormat? = null,
    val user: String? = null
): MultiPartFormDataRequest {
    override fun toMultiPartFormData(): MultiPartFormDataContent {
        return MultiPartFormDataContent(
            formData {
                appendImage("image", image)
                appendIfNotNull("mask", mask)
                append("prompt", prompt)
                appendIfNotNull("n", n)
                appendIfNotNull("size", size?.value)
                appendIfNotNull("response_format", responseFormat?.value)
                appendIfNotNull("user", user)
            }
        )
    }
}

@kotlinx.serialization.Serializable
data class VariateImageRequest(
    val image: ByteArray,
    val n: Int? = null,
    val size: ImageSize? = null,
    @SerialName("response_format")
    val responseFormat: ImageFormat? = null,
    val user: String? = null
): MultiPartFormDataRequest {
    override fun toMultiPartFormData(): MultiPartFormDataContent {
        return MultiPartFormDataContent(
            formData {
                appendImage("image", image)
                appendIfNotNull("n", n)
                appendIfNotNull("size", size?.value)
                appendIfNotNull("response_format", responseFormat?.value)
                appendIfNotNull("user", user)
            }
        )
    }
}

@kotlinx.serialization.Serializable
data class ImageResult(
    val created: Long,
    val data: List<ImageData>
)

@JsExport
@kotlinx.serialization.Serializable
data class ImageData(
    val url: String? = null,
    @SerialName("b64_json")
    val base64: String? = null,
)

@JsExport
@kotlinx.serialization.Serializable
enum class ImageSize(val value: String) {
    @SerialName("256x256")
    SMALL("256x256"),
    @SerialName("512x512")
    MEDIUM("512x512"),
    @SerialName("1024x1024")
    LARGE("1024x1024")
}

@JsExport
@kotlinx.serialization.Serializable
enum class ImageFormat(val value: String) {
    @SerialName("url")
    URL("url"),
    @SerialName("b64_json")
    BASE64("b64_json"),
}