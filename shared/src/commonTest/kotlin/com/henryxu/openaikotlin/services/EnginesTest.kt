package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.BaseServiceTest
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class EnginesTest: BaseServiceTest() {
    @Test
    fun testExampleListEngines() = runTest {
        val result = runValidCase { client.api.listEngines() }
        assertNotEquals(0, result.data.size)
    }

    @Test
    fun testExampleRetrieveEngine() = runTest {
        val model = "text-davinci-003"
        val result = runValidCase { client.api.retrieveEngine(model) }
        assertEquals(model, result.id)
    }
}