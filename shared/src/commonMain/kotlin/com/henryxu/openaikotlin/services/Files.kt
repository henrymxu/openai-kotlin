package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.Response
import com.henryxu.openaikotlin.models.EntityDeleteResult
import com.henryxu.openaikotlin.models.FileResult
import com.henryxu.openaikotlin.models.FilesResult
import com.henryxu.openaikotlin.models.UploadFileRequest

/**
 * https://beta.openai.com/docs/api-reference/files
 */
interface FilesApi {
    suspend fun listFiles(): Response<FilesResult>
    suspend fun retrieveFile(fileId: String): Response<FileResult>
    suspend fun retrieveFileContent(fileId: String): Response<Any>
    suspend fun uploadFile(request: UploadFileRequest): Response<FileResult>
    suspend fun deleteFile(fileId: String): Response<EntityDeleteResult>
}
