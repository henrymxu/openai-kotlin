package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.Response
import com.henryxu.openaikotlin.models.CreateEmbeddingsRequest
import com.henryxu.openaikotlin.models.EmbeddingResult

/**
 * https://beta.openai.com/docs/api-reference/embeddings
 */
interface EmbeddingsApi {
    suspend fun createEmbeddings(request: CreateEmbeddingsRequest): Response<EmbeddingResult>
}
