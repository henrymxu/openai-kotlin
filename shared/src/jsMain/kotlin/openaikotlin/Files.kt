package openaikotlin

import com.henrymxu.openaikotlin.models.File
import com.henrymxu.openaikotlin.models.FileResult
import com.henrymxu.openaikotlin.models.FilesResult
import com.henrymxu.openaikotlin.models.UploadFileRequest

@JsExport
@JsName("UploadFileRequest")
external interface UploadFileRequestWrapper {
    val file: ByteArray
    val fileName: String
    val purpose: String
}

fun UploadFileRequestWrapper.unwrap() = UploadFileRequest(file, fileName, purpose)

//@JsExport
//@JsName("UploadFileRequest")
//data class UploadFileRequestWrapper(
//    val file: ByteArray,
//    val fileName: String,
//    val purpose: String
//): RequestWrapper<UploadFileRequest> {
//    @Suppress("NON_EXPORTABLE_TYPE")
//    override fun unwrap() = UploadFileRequest(file, fileName, purpose)
//}

@JsExport
@JsName("FilesResult")
data class FilesResultWrapper(
    val data: Array<File>,
    val type: String
)

fun FilesResult.wrap(): FilesResultWrapper {
    return FilesResultWrapper(data.toTypedArray(), type)
}

typealias FileResultWrapper = File

fun FileResult.wrap(): FileResultWrapper {
    return FileResultWrapper(id, type, bytes, createdAt, fileName, purpose)
}
