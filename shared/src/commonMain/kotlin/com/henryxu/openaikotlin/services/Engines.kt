package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.Response
import com.henryxu.openaikotlin.models.EngineResult
import com.henryxu.openaikotlin.models.EnginesResult
import io.ktor.resources.*

@Deprecated(
    "Please use their replacement, Models, instead. ",
    ReplaceWith("ModelsApi")
)
/**
 * https://beta.openai.com/docs/api-reference/engines
 */
interface EnginesApi {
    suspend fun listEngines(): Response<EnginesResult>
    suspend fun retrieveEngine(engineId: String): Response<EngineResult>
}

@kotlinx.serialization.Serializable
@Resource("/engines")
internal class EnginesResource {
    @kotlinx.serialization.Serializable
    @Resource("{id}")
    class EngineResource(val id: String, val parent: EnginesResource = EnginesResource())
}