package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.Response
import com.henryxu.openaikotlin.models.CreateFineTuneRequest
import com.henryxu.openaikotlin.models.EntityDeleteResult
import com.henryxu.openaikotlin.models.FineTuneEventResult
import com.henryxu.openaikotlin.models.FineTuneEventsResult
import com.henryxu.openaikotlin.models.FineTuneResult
import com.henryxu.openaikotlin.models.FineTunesResult
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
