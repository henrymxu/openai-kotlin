package com.henrymxu.openaikotlin.services

import com.henrymxu.openaikotlin.Response
import com.henrymxu.openaikotlin.models.CreateEditRequest
import com.henrymxu.openaikotlin.models.EditResult

/**
 * https://beta.openai.com/docs/api-reference/edits
 */
interface EditsApi {
    suspend fun createEdit(request: CreateEditRequest): Response<EditResult>
}
