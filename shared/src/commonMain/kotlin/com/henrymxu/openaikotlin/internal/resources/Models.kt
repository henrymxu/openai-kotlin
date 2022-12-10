package com.henrymxu.openaikotlin.internal.resources

import io.ktor.resources.Resource

@kotlinx.serialization.Serializable
@Resource("/models")
internal class ModelsResource {
    @kotlinx.serialization.Serializable
    @Resource("{id}")
    class ModelResource(val id: String, val parent: ModelsResource = ModelsResource())
}
