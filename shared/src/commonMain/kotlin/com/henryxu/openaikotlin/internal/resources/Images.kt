package com.henryxu.openaikotlin.internal.resources

import io.ktor.resources.Resource

@kotlinx.serialization.Serializable
@Resource("/images")
internal class ImagesResource {
    @kotlinx.serialization.Serializable
    @Resource("generations")
    class ImageCreateResource(val parent: ImagesResource = ImagesResource())

    @kotlinx.serialization.Serializable
    @Resource("edits")
    class ImageEditResource(val parent: ImagesResource = ImagesResource())

    @kotlinx.serialization.Serializable
    @Resource("variations")
    class ImageVariateResource(val parent: ImagesResource = ImagesResource())
}
