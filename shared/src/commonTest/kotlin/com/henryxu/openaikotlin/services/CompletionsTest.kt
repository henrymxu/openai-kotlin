package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.BaseServiceTest
import com.henryxu.openaikotlin.models.CreateCompletionRequest
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class CompletionsTest: BaseServiceTest() {
    @Test
    fun testExampleCreateCompletion() = runTest {
        val model = "text-davinci-003"
        val request = CreateCompletionRequest(
            model,
            prompt = "Say this is a test",
            maxTokens = 7,
            temperature = 0.0,
            topP = 1.0,
            n = 1,
            stream = false,
            logprobs = null,
            stop = listOf("\n")
        )

        val result = runValidCase { client.api.createCompletion(request) }
        assertEquals(model, result.model)
    }
}