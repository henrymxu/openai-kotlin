package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.Response
import com.henryxu.openaikotlin.models.ModelResult
import com.henryxu.openaikotlin.models.ModelsResult

/**
 * https://beta.openai.com/docs/api-reference/models
 */
interface ModelsApi {
    suspend fun listModels(): Response<ModelsResult>
    suspend fun retrieveModel(modelId: String): Response<ModelResult>
}
