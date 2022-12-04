package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.BaseServiceTest
import com.henryxu.openaikotlin.models.CreateEmbeddingsRequest
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class EmbeddingsTest: BaseServiceTest() {
    @Test
    fun testExampleCreateEmbeddings() = runTest {
        val request = CreateEmbeddingsRequest(
            "text-similarity-babbage-001",
            input = "The food was delicious and the waiter...",
        )
        val result = runValidCase { client.api.createEmbeddings(request) }
        assertNotEquals(0, result.data.size)
    }
}