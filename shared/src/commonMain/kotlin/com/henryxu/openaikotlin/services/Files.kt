package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.Response
import com.henryxu.openaikotlin.models.EntityDeleteResult
import com.henryxu.openaikotlin.models.FileResult
import com.henryxu.openaikotlin.models.FilesResult
import com.henryxu.openaikotlin.models.UploadFileRequest
import io.ktor.resources.*

interface FilesApi {
    suspend fun listFiles(): Response<FilesResult>
    suspend fun retrieveFile(fileId: String): Response<FileResult>
    suspend fun retrieveFileContent(fileId: String): Response<Any>
    suspend fun uploadFile(request: UploadFileRequest): Response<FileResult>
    suspend fun deleteFile(fileId: String): Response<EntityDeleteResult>
}

@kotlinx.serialization.Serializable
@Resource("/MR/files")
internal class FilesResource {
    @kotlinx.serialization.Serializable
    @Resource("{id}")
    class FileResource(val id: String, val parent: FilesResource = FilesResource())

    @kotlinx.serialization.Serializable
    @Resource("{id}/content")
    class FileContentResource(val id: String, val parent: FilesResource = FilesResource())
}
