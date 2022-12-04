package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.Response
import com.henryxu.openaikotlin.models.CompletionResult
import com.henryxu.openaikotlin.models.CreateCompletionRequest
import io.ktor.resources.Resource

/**
 * https://beta.openai.com/docs/api-reference/completions
 */
interface CompletionsApi {
    suspend fun createCompletion(request: CreateCompletionRequest): Response<CompletionResult>
}

@kotlinx.serialization.Serializable
@Resource("/completions")
internal class CompletionsResource
