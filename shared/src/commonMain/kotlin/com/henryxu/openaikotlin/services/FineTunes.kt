package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.Response
import com.henryxu.openaikotlin.models.*
import io.ktor.resources.*

interface FineTunesApi {
    suspend fun createFineTune(request: CreateFineTuneRequest): Response<FineTuneResult>
    suspend fun listFineTunes(): Response<FineTunesResult>
    suspend fun retrieveFineTune(fineTuneId: String): Response<FineTuneResult>
    suspend fun cancelFineTune(fineTuneId: String): Response<FineTuneResult>
    suspend fun listFineTuneEvents(
        fineTuneId: String,
        stream: Boolean? = null
    ): Response<FineTuneEventsResult>
    suspend fun deleteFineTuneModel(modelId: String): Response<EntityDeleteResult>
}

@kotlinx.serialization.Serializable
@Resource("/fine-tunes")
internal class FineTunesResource {
    @kotlinx.serialization.Serializable
    @Resource("{id}")
    class FineTuneResource(val id: String, val parent: FineTunesResource = FineTunesResource())

    @kotlinx.serialization.Serializable
    @Resource("{id}/cancel")
    class FineTuneCancelResource(val id: String, val parent: FineTunesResource = FineTunesResource())

    @kotlinx.serialization.Serializable
    @Resource("{id}/events")
    class FineTuneEventsResource(val id: String, val stream: Boolean? = null, val parent: FineTunesResource = FineTunesResource())
}
