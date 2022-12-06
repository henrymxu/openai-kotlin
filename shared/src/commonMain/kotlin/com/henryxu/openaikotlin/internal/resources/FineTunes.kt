package com.henryxu.openaikotlin.internal.resources

import com.henryxu.openaikotlin.internal.StreamingSupportedRequest
import com.henryxu.openaikotlin.models.*
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
