package com.henryxu.openaikotlin.internal

import io.ktor.client.plugins.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class OpenAiUtilsTest {
    @Test
    fun testHandleClientRequestException() {
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
}