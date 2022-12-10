package com.henrymxu.openaikotlin.services

import com.henrymxu.openaikotlin.Response
import com.henrymxu.openaikotlin.models.CreateEmbeddingsRequest
import com.henrymxu.openaikotlin.models.EmbeddingResult

/**
 * https://beta.openai.com/docs/api-reference/embeddings
 */
interface EmbeddingsApi {
    suspend fun createEmbeddings(request: CreateEmbeddingsRequest): Response<EmbeddingResult>
}
