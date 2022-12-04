package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.BaseServiceTest
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class ModelsTest: BaseServiceTest() {
    @Test
    fun testExampleListModels() = runTest {
        val result = runValidCase { client.api.listModels() }
        assertNotEquals(0, result.data.size)
    }

    @Test
    fun testExampleRetrieveModel() = runTest {
        val model = "text-davinci-003"
        val result = runValidCase { client.api.retrieveModel(model) }
        assertEquals(model, result.id)
    }
}