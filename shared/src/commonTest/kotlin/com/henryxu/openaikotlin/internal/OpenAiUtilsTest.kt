package com.henryxu.openaikotlin.internal

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class OpenAiUtilsTest {
    @Test
    fun testHandleInappropriateContent() {
        val exampleError = """
            Client request(POST https://api.openai.com/v1/images/generations) invalid: 400 Bad Request. Text: "{
              "error": {
                "code": null,
                "message": "Your request was rejected as a result of our safety system. Your prompt may contain text that is not allowed by our safety system.",
                "param": null,
                "type": "invalid_request_error"
              }
            }
            "
        """.trimIndent()
        val error = OpenAiUtils.parseOpenAiClientRequestError(exampleError)
        assertNotNull(error)
        assertEquals("invalid_request_error", error.type)
    }

    @Test
    fun testHandleInvalidApiKey() {
        val exampleError = """
            Client request(POST https://api.openai.com/v1/completions) invalid: 401 . Text: "{
                "error": {
                    "message": "Incorrect API key provided: undefined. You can find your API key at https://beta.openai.com.",
                    "type": "invalid_request_error",
                    "param": null,
                    "code": "invalid_api_key"
                }
            }
            "
        """.trimIndent()
        val error = OpenAiUtils.parseOpenAiClientRequestError(exampleError)
        assertNotNull(error)
        assertEquals("invalid_request_error", error.type)
        assertEquals("invalid_api_key", error.code)

    }
}