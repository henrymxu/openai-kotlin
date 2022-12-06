package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.BaseServiceTest
import com.henryxu.openaikotlin.STREAM_TIMEOUT_MS
import com.henryxu.openaikotlin.models.CreateCompletionRequest
import com.henryxu.openaikotlin.models.CreateFineTuneRequest
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class FineTunesTest: BaseServiceTest() {
    @Test
    fun testExampleCreateFineTune() = runTest {
        val request = CreateFineTuneRequest(
            "file-XGinujblHPwGLSztz8cPS8XY"
        )
        val result = runValidCase { client.api.createFineTune(request) }
        assertTrue { result.id.startsWith("ft-") }
    }

    @Test
    fun testExampleListFineTunes() = runTest {
        val result = runValidCase { client.api.listFineTunes() }
        assertEquals("list", result.type)
    }

    @Test
    fun testExampleRetrieveFineTune() = runTest {
        val fineTuneId = "ft-AF1WoRqd3aJAHsqc9NY7iL8F"
        val result = runValidCase { client.api.retrieveFineTune(fineTuneId) }
        assertEquals(fineTuneId, result.id)
    }

    @Test
    fun testExampleCancelFineTune() = runTest {
        val fineTuneId = "ft-AF1WoRqd3aJAHsqc9NY7iL8F"
        val result = runValidCase { client.api.cancelFineTune(fineTuneId) }
        assertEquals(fineTuneId, result.id)
        assertEquals("cancelled", result.status)
    }

    @Test
    fun testExampleListFineTuneEvents() = runTest {
        val fineTuneId = "ft-AF1WoRqd3aJAHsqc9NY7iL8F"
        val result = runValidCase { client.api.listFineTuneEvents(fineTuneId) }
        assertEquals("list", result.type)
    }

    @Test
    fun testExampleDeleteFineTuneModel() = runTest {
        val fineTuneId = "curie:ft-acmeco-2021-03-03-21-44-20"
        val result = runValidCase { client.api.deleteFineTuneModel(fineTuneId) }
        assertTrue(result.deleted)
    }

    @Test
    fun testStreamFineTuneEvents() = runTest {
        // TODO: find the response for stream
        val fineTuneId = "ft-AF1WoRqd3aJAHsqc9NY7iL8F"
        val result = client.api.streamFineTuneEvents(fineTuneId)
        val list = withTimeoutOrNull(STREAM_TIMEOUT_MS) {
            return@withTimeoutOrNull result.take(2).toList()
        }
//        assertNotNull(list)
//        assertEquals(2, list.size)
//
//        val firstResult = list.first().result
//        assertNotNull(firstResult)
    }
}