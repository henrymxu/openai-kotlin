package com.henrymxu.openaikotlin.services

import com.henrymxu.openaikotlin.BaseServiceTest
import com.henrymxu.openaikotlin.models.CreateEditRequest
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertNotEquals

class EditsTest : BaseServiceTest() {
    @Test
    fun testExampleCreateEdit() = runTest {
        val request = CreateEditRequest(
            "text-davinci-edit-001",
            input = "What day of the wek is it?",
            instruction = "Fix the spelling mistakes"
        )
        val response = """
            {
              "object": "edit",
              "created": 1589478378,
              "choices": [
                {
                  "text": "What day of the week is it?",
                  "index": 0
                }
              ],
              "usage": {
                "prompt_tokens": 25,
                "completion_tokens": 32,
                "total_tokens": 57
              }
            }
        """.trimIndent()
        val result = mockValidApiRequest(response) {
            createEdit(request)
        }
        assertNotEquals(0, result.choices.size)
    }
}
