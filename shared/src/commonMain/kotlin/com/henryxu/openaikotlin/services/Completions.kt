package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.Response
import com.henryxu.openaikotlin.models.CompletionResult
import com.henryxu.openaikotlin.models.CreateCompletionRequest
import kotlinx.coroutines.flow.Flow

/**
 * https://beta.openai.com/docs/api-reference/completions
 */
interface CompletionsApi {
    suspend fun createCompletion(request: CreateCompletionRequest): Response<CompletionResult>

    suspend fun streamCreateCompletion(request: CreateCompletionRequest): Flow<Response<CompletionResult>>
}
