package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.Response
import com.henryxu.openaikotlin.models.ModelResult
import com.henryxu.openaikotlin.models.ModelsResult
import io.ktor.resources.*

interface ModelsApi {
    suspend fun listModels(): Response<ModelsResult>
    suspend fun retrieveModel(modelId: String): Response<ModelResult>
}

@kotlinx.serialization.Serializable
@Resource("/models")
internal class ModelsResource {
    @kotlinx.serialization.Serializable
    @Resource("{id}")
    class ModelResource(val id: String, val parent: ModelsResource = ModelsResource())
}
