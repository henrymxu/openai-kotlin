package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.Response
import com.henryxu.openaikotlin.models.CreateEditRequest
import com.henryxu.openaikotlin.models.EditResult
import io.ktor.resources.*

/**
 * https://beta.openai.com/docs/api-reference/edits
 */
interface EditsApi {
    suspend fun createEdit(request: CreateEditRequest): Response<EditResult>
}

@kotlinx.serialization.Serializable
@Resource("/edits")
internal class EditsResource

