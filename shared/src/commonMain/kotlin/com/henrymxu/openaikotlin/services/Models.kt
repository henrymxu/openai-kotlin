package com.henrymxu.openaikotlin.services

import com.henrymxu.openaikotlin.Response
import com.henrymxu.openaikotlin.models.ModelResult
import com.henrymxu.openaikotlin.models.ModelsResult

/**
 * https://beta.openai.com/docs/api-reference/models
 */
interface ModelsApi {
    suspend fun listModels(): Response<ModelsResult>
    suspend fun retrieveModel(modelId: String): Response<ModelResult>
}
