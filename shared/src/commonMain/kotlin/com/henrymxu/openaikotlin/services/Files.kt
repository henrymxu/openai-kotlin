package com.henrymxu.openaikotlin.services

import com.henrymxu.openaikotlin.Response
import com.henrymxu.openaikotlin.models.EntityDeleteResult
import com.henrymxu.openaikotlin.models.FileResult
import com.henrymxu.openaikotlin.models.FilesResult
import com.henrymxu.openaikotlin.models.UploadFileRequest

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
