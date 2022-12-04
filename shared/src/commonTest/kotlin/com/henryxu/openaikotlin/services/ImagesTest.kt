package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.BaseServiceTest
import com.henryxu.openaikotlin.models.CreateImageRequest
import com.henryxu.openaikotlin.models.EditImageRequest
import com.henryxu.openaikotlin.models.ImageSize
import com.henryxu.openaikotlin.models.VariateImageRequest
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertNotEquals

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
        println(result.data.first().url)
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
        println(result.data.first().url)
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
        println(result.data.first().url)
        assertNotEquals("", result.data.first().url)
    }
}