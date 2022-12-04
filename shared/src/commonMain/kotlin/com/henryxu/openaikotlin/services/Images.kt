package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.Response
import com.henryxu.openaikotlin.models.CreateImageRequest
import com.henryxu.openaikotlin.models.EditImageRequest
import com.henryxu.openaikotlin.models.ImageResult
import com.henryxu.openaikotlin.models.VariateImageRequest
import io.ktor.resources.*

/**
 * https://beta.openai.com/docs/api-reference/images
 */
interface ImagesApi {
    suspend fun createImage(request: CreateImageRequest): Response<ImageResult>
    suspend fun editImage(request: EditImageRequest): Response<ImageResult>
    suspend fun variateImage(request: VariateImageRequest): Response<ImageResult>
}

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
