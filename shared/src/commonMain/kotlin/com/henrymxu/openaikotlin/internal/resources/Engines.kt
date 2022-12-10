package com.henrymxu.openaikotlin.internal.resources

import io.ktor.resources.Resource

@kotlinx.serialization.Serializable
@Resource("/engines")
internal class EnginesResource {
    @kotlinx.serialization.Serializable
    @Resource("{id}")
    class EngineResource(val id: String, val parent: EnginesResource = EnginesResource())
}