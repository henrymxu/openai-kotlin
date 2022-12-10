package com.henrymxu.openaikotlin.services

import com.henrymxu.openaikotlin.Response
import com.henrymxu.openaikotlin.models.CreateModerationRequest
import com.henrymxu.openaikotlin.models.ModerationResult

/**
 * https://beta.openai.com/docs/api-reference/moderations
 */
interface ModerationsApi {
    suspend fun createModeration(request: CreateModerationRequest): Response<ModerationResult>
}
