package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.BaseServiceTest
import com.henryxu.openaikotlin.STREAM_TIMEOUT_MS
import com.henryxu.openaikotlin.models.CreateCompletionRequest
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

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

    @Test
    fun testStreamCreateCompletion() = runTest {
        val model = "text-davinci-003"
        val request = CreateCompletionRequest(
            model,
            prompt = "Say this is a test",
            maxTokens = 7,
            temperature = 0.0,
            topP = 1.0,
            n = 2,
            stream = true,
            logprobs = null,
            stop = listOf("\n")
        )

        val result = client.api.streamCreateCompletion(request)
        val list = withTimeoutOrNull(STREAM_TIMEOUT_MS) {
            return@withTimeoutOrNull result.take(2).toList()
        }
        assertNotNull(list)
        assertEquals(2, list.size)

        val firstResult = list.first().result
        assertNotNull(firstResult)
        assertNotEquals(0, firstResult.choices.size)
    }
}