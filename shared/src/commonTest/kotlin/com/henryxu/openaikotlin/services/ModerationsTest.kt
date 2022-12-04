package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.BaseServiceTest
import com.henryxu.openaikotlin.models.CreateModerationRequest
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertNotEquals

class ModerationsTest: BaseServiceTest() {
    @Test
    fun testExampleCreateModeration() = runTest {
        val request = CreateModerationRequest(
            "I want to kill them."
        )

        val result = runValidCase { client.api.createModeration(request) }
        assertNotEquals(0, result.results.size)
    }
}