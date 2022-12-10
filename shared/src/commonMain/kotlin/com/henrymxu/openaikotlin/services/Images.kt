package com.henrymxu.openaikotlin.services

import com.henrymxu.openaikotlin.Response
import com.henrymxu.openaikotlin.models.CreateImageRequest
import com.henrymxu.openaikotlin.models.EditImageRequest
import com.henrymxu.openaikotlin.models.ImageResult
import com.henrymxu.openaikotlin.models.VariateImageRequest

/**
 * https://beta.openai.com/docs/api-reference/images
 */
interface ImagesApi {
    suspend fun createImage(request: CreateImageRequest): Response<ImageResult>
    suspend fun editImage(request: EditImageRequest): Response<ImageResult>
    suspend fun variateImage(request: VariateImageRequest): Response<ImageResult>
}
