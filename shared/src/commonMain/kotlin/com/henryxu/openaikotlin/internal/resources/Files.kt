package com.henryxu.openaikotlin.internal.resources

import io.ktor.resources.Resource

@kotlinx.serialization.Serializable
@Resource("/files")
internal class FilesResource {
    @kotlinx.serialization.Serializable
    @Resource("{id}")
    class FileResource(val id: String, val parent: FilesResource = FilesResource())

    @kotlinx.serialization.Serializable
    @Resource("{id}/content")
    class FileContentResource(val id: String, val parent: FilesResource = FilesResource())
}
