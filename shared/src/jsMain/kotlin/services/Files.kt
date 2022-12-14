package services

import com.henrymxu.openaikotlin.models.EntityDeleteResult
import ResponseWrapper
import FileResultWrapper
import FilesResultWrapper
import UploadFileRequestWrapper
import kotlin.js.Promise

/**
 * @see com.henrymxu.openaikotlin.services.FilesApi
 */
@JsExport
@JsName("FilesApi")
interface FilesApiWrapper {
    fun listFiles(): Promise<ResponseWrapper<FilesResultWrapper>>
    fun retrieveFile(fileId: String): Promise<ResponseWrapper<FileResultWrapper>>
    fun retrieveFileContent(fileId: String): Promise<ResponseWrapper<Any>>
    fun uploadFile(request: UploadFileRequestWrapper): Promise<ResponseWrapper<FileResultWrapper>>
    fun deleteFile(fileId: String): Promise<ResponseWrapper<EntityDeleteResult>>
}
