package com.henrymxu.openaikotlin.models

import com.henrymxu.openaikotlin.internal.MultiPartFormDataRequest
import com.henrymxu.openaikotlin.models.Utils.appendFile
import io.ktor.client.request.forms.*
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class UploadFileRequest(
    val file: ByteArray,
    val fileName: String,
    val purpose: String
): MultiPartFormDataRequest {
    override fun toMultiPartFormData(): MultiPartFormDataContent {
        return MultiPartFormDataContent(
            formData {
                appendFile("file", file, fileName)
                append("purpose", purpose)
            }
        )
    }
}

@kotlinx.serialization.Serializable
data class FilesResult(
    val data: List<File>,
    @SerialName("object")
    val type: String
)

typealias FileResult = File