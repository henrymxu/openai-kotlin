package com.henrymxu.openaikotlin.services

import com.henrymxu.openaikotlin.Response
import com.henrymxu.openaikotlin.models.CompletionResult
import com.henrymxu.openaikotlin.models.CreateCompletionRequest
import kotlinx.coroutines.flow.Flow

/**
 * https://beta.openai.com/docs/api-reference/completions
 */
interface CompletionsApi {
    suspend fun createCompletion(request: CreateCompletionRequest): Response<CompletionResult>

    suspend fun streamCreateCompletion(request: CreateCompletionRequest): Flow<Response<CompletionResult>>
}
