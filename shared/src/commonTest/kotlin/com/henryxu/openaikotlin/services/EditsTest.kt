package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.BaseServiceTest
import com.henryxu.openaikotlin.models.CreateEditRequest
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertNotEquals

class EditsTest: BaseServiceTest() {
    @Test
    fun testExampleCreateEdit() = runTest {
        val request = CreateEditRequest(
            "text-davinci-edit-001",
            input = "What day of the wek is it?",
            instruction = "Fix the spelling mistakes"
        )
        val result = runValidCase { client.api.createEdit(request) }
        assertNotEquals(0, result.choices.size)
    }
}