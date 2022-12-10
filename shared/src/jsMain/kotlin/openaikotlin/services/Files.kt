package openaikotlin.services

import com.henrymxu.openaikotlin.models.EntityDeleteResult
import openaikotlin.ResponseWrapper
import openaikotlin.FileResultWrapper
import openaikotlin.FilesResultWrapper
import openaikotlin.UploadFileRequestWrapper
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
