package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.BaseServiceTest
import com.henryxu.openaikotlin.models.*
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.*

class ImagesTest: BaseServiceTest() {
    @Test
    fun testExampleCreateImage() = runTest {
        val request = CreateImageRequest(
            "A cute baby sea otter",
            n = 2,
            size = ImageSize.LARGE
        )
        val result = runValidCase { client.api.createImage(request) }
        assertNotEquals(0, result.data.size)
        assertNotEquals("", result.data.first().url)
    }

    @Test
    fun testExampleEditImage() = runTest {
        val request = EditImageRequest(
            openFile("images/otter.png"),
            prompt = "A cute baby sea otter wearing a beret",
            n = 2,
            size = ImageSize.LARGE
        )
        val result = runValidCase { client.api.editImage(request) }
        assertNotEquals(0, result.data.size)
        assertNotEquals("", result.data.first().url)
    }

    @Test
    fun testExampleVariateImage() = runTest {
        val request = VariateImageRequest(
            openFile("images/otter.png"),
            n = 2,
            size = ImageSize.LARGE
        )
        val result = runValidCase { client.api.variateImage(request) }
        assertNotEquals(0, result.data.size)
        assertNotEquals("", result.data.first().url)
    }

    @Test
    fun testInappropriateCreateImage() = runTest {
        val request = CreateImageRequest(
            "Shooting someone",
            n = 2,
            size = ImageSize.SMALL
        )
        val result = runInvalidCase { client.api.createImage(request) }
        assertEquals("invalid_request_error", result.type)
        assertTrue(result.message.isNotBlank())
    }
}