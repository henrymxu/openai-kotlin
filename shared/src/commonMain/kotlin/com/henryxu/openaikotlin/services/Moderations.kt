package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.Response
import com.henryxu.openaikotlin.models.CreateModerationRequest
import com.henryxu.openaikotlin.models.ModerationResult
import io.ktor.resources.*

interface ModerationsApi {
    suspend fun createModeration(request: CreateModerationRequest): Response<ModerationResult>
}

@kotlinx.serialization.Serializable
@Resource("/moderations")
internal class ModerationsResource
