package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.BaseServiceTest
import com.henryxu.openaikotlin.models.CreateImageRequest
import com.henryxu.openaikotlin.models.EditImageRequest
import com.henryxu.openaikotlin.models.ImageSize
import com.henryxu.openaikotlin.models.VariateImageRequest
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class ImagesTest : BaseServiceTest() {
    @Test
    fun testExampleCreateImage() = runTest {
        val request = CreateImageRequest(
            "A cute baby sea otter",
            n = 2,
            size = ImageSize.LARGE
        )
        val response = """
            {
              "created": 1589478378,
              "data": [
                {
                  "url": "https://..."
                },
                {
                  "url": "https://..."
                }
              ]
            }
        """.trimIndent()
        val result = mockValidApiRequest(response) {
            createImage(request)
        }
        assertNotEquals(0, result.data.size)
        assertNotEquals("", result.data.first().url)
    }

    @Test
    fun testExampleEditImage() = runTest {
        val request = EditImageRequest(
            ByteArray(0),
            prompt = "A cute baby sea otter wearing a beret",
            n = 2,
            size = ImageSize.LARGE
        )
        val response = """
            {
              "created": 1589478378,
              "data": [
                {
                  "url": "https://..."
                },
                {
                  "url": "https://..."
                }
              ]
            }
        """.trimIndent()
        val result = mockValidApiRequest(response) {
            editImage(request)
        }
        assertNotEquals(0, result.data.size)
        assertNotEquals("", result.data.first().url)
    }

    @Test
    fun testExampleVariateImage() = runTest {
        val request = VariateImageRequest(
            ByteArray(0),
            n = 2,
            size = ImageSize.LARGE
        )
        val response = """
            {
              "created": 1589478378,
              "data": [
                {
                  "url": "https://..."
                },
                {
                  "url": "https://..."
                }
              ]
            }
        """.trimIndent()
        val result = mockValidApiRequest(response) {
            variateImage(request)
        }
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
        val response = """
            {
                "error": {
                    "message": "Incorrect API key provided: undefined. You can find your API key at https://beta.openai.com.",
                    "type": "invalid_request_error",
                    "param": null,
                    "code": "invalid_api_key"
                }
            }
        """.trimIndent()
        val result = mockInvalidApiRequest(response) {
            createImage(request)
        }
        assertEquals("invalid_request_error", result.type)
        assertTrue(result.message.isNotBlank())
    }
}