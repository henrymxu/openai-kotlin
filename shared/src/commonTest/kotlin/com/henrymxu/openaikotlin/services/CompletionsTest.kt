package com.henrymxu.openaikotlin.services

import com.henrymxu.openaikotlin.BaseServiceTest
import com.henrymxu.openaikotlin.models.CreateCompletionRequest
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

class CompletionsTest : BaseServiceTest() {
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
        val response = """
            {
              "id": "cmpl-uqkvlQyYK7bGYrRHQ0eXlWi7",
              "object": "text_completion",
              "created": 1589478378,
              "model": "text-davinci-003",
              "choices": [
                {
                  "text": "\n\nThis is indeed a test",
                  "index": 0,
                  "logprobs": null,
                  "finish_reason": "length"
                }
              ],
              "usage": {
                "prompt_tokens": 5,
                "completion_tokens": 7,
                "total_tokens": 12
              }
            }
        """.trimIndent()
        val result = mockValidApiRequest(response) {
            createCompletion(request)
        }
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

        val response = listOf(
            """data: {"id":"response1","object":"text_completion","created":1589478378,"model":"text-davinci-003","choices":[{"text": "\n\nThis is indeed a test","index": 0,"logprobs": null,"finish_reason": "length"}]}""",
            """data: {"id":"response2","object":"text_completion","created":1589478378,"model":"text-davinci-003","choices":[{"text": "\n\nThis is indeed a test","index": 0,"logprobs": null,"finish_reason": "length"}]}""",
            """[DONE]"""
            )

        val result = mockStreamApiRequest(response) {
            streamCreateCompletion(request)
        }

        assertEquals(2, result.size)

        val firstResult = result.first().result
        assertNotNull(firstResult)
        assertNotEquals(0, firstResult.choices.size)
        assertEquals("response1", firstResult.id)
    }
}