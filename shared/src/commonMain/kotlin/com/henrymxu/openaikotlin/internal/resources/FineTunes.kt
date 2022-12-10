package com.henrymxu.openaikotlin.internal.resources

import com.henrymxu.openaikotlin.internal.StreamingSupportedRequest
import com.henrymxu.openaikotlin.models.*
import io.ktor.resources.Resource

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
    class FineTuneEventsResource(val id: String, val stream: Boolean? = null, val parent: FineTunesResource = FineTunesResource()): StreamingSupportedRequest {
        override fun isStreamRequest(): Boolean {
            return stream == true
        }
    }
}
