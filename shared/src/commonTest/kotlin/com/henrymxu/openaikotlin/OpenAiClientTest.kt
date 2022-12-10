package com.henrymxu.openaikotlin

import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class OpenAiClientTest : BaseServiceTest() {
    @Test
    fun testInvalidApiKey() = runTest {
        val response = """
            {
                "error": {
                    "message": "Incorrect API key provided: undefined. You can find your API key at https://beta.openai.com.",
                    "type": "invalid_request_error",
                    "param": null,
                    "code": "invalid_api_key"
                }
            }
        """.trimIndent()
        val error = mockInvalidApiRequest(response) {
            listModels()
        }
        assertEquals("invalid_api_key", error.code)
    }
}