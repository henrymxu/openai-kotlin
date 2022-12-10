package com.henrymxu.openaikotlin.services

import com.henrymxu.openaikotlin.BaseServiceTest
import com.henrymxu.openaikotlin.models.CreateEmbeddingsRequest
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertNotEquals

class EmbeddingsTest : BaseServiceTest() {
    @Test
    fun testExampleCreateEmbeddings() = runTest {
        val request = CreateEmbeddingsRequest(
            "text-similarity-babbage-001",
            input = "The food was delicious and the waiter...",
        )
        val response = """
            {
              "object": "list",
              "data": [
                {
                  "object": "embedding",
                  "embedding": [
                    0.018990106880664825,
                    -0.0073809814639389515,
                    -0.0073809814639389515,
                    0.021276434883475304
                  ],
                  "index": 0
                }
              ],
              "usage": {
                "prompt_tokens": 8,
                "total_tokens": 8
              }
            }
        """.trimIndent()
        val result = mockValidApiRequest(response) {
            createEmbeddings(request)
        }
        assertNotEquals(0, result.data.size)
    }
}