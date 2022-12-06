package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.Response
import com.henryxu.openaikotlin.models.EngineResult
import com.henryxu.openaikotlin.models.EnginesResult

/**
 * https://beta.openai.com/docs/api-reference/engines
 */
@Deprecated(
    "Please use their replacement, Models, instead. ",
    ReplaceWith("ModelsApi")
)
interface EnginesApi {
    suspend fun listEngines(): Response<EnginesResult>
    suspend fun retrieveEngine(engineId: String): Response<EngineResult>
}
