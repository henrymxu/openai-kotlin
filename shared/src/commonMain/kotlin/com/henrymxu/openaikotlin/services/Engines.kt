package com.henrymxu.openaikotlin.services

import com.henrymxu.openaikotlin.Response
import com.henrymxu.openaikotlin.models.EngineResult
import com.henrymxu.openaikotlin.models.EnginesResult

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
