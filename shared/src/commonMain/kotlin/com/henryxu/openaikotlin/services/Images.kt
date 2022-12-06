package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.Response
import com.henryxu.openaikotlin.models.CreateImageRequest
import com.henryxu.openaikotlin.models.EditImageRequest
import com.henryxu.openaikotlin.models.ImageResult
import com.henryxu.openaikotlin.models.VariateImageRequest

/**
 * https://beta.openai.com/docs/api-reference/images
 */
interface ImagesApi {
    suspend fun createImage(request: CreateImageRequest): Response<ImageResult>
    suspend fun editImage(request: EditImageRequest): Response<ImageResult>
    suspend fun variateImage(request: VariateImageRequest): Response<ImageResult>
}
