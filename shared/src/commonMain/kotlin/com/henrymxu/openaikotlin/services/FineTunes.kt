package com.henrymxu.openaikotlin.services

import com.henrymxu.openaikotlin.Response
import com.henrymxu.openaikotlin.models.CreateFineTuneRequest
import com.henrymxu.openaikotlin.models.EntityDeleteResult
import com.henrymxu.openaikotlin.models.FineTuneEventResult
import com.henrymxu.openaikotlin.models.FineTuneEventsResult
import com.henrymxu.openaikotlin.models.FineTuneResult
import com.henrymxu.openaikotlin.models.FineTunesResult
import kotlinx.coroutines.flow.Flow

/**
 * https://beta.openai.com/docs/api-reference/fine-tunes
 */
interface FineTunesApi {
    suspend fun createFineTune(request: CreateFineTuneRequest): Response<FineTuneResult>
    suspend fun listFineTunes(): Response<FineTunesResult>
    suspend fun retrieveFineTune(fineTuneId: String): Response<FineTuneResult>
    suspend fun cancelFineTune(fineTuneId: String): Response<FineTuneResult>
    suspend fun listFineTuneEvents(
        fineTuneId: String
    ): Response<FineTuneEventsResult>

    suspend fun streamFineTuneEvents(fineTuneId: String): Flow<Response<FineTuneEventResult>>
    suspend fun deleteFineTuneModel(modelId: String): Response<EntityDeleteResult>
}
