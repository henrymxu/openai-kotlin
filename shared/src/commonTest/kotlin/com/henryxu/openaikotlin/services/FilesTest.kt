package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.BaseServiceTest
import com.henryxu.openaikotlin.models.UploadFileRequest
import com.henryxu.openaikotlin.MockSingleResponse
import io.ktor.utils.io.core.toByteArray
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FilesTest: BaseServiceTest() {
    @Test
    fun testExampleListFiles() = runTest {
        val response = """
            {
              "data": [
                {
                  "id": "file-ccdDZrC3iZVNiQVeEA6Z66wf",
                  "object": "file",
                  "bytes": 175,
                  "created_at": 1613677385,
                  "filename": "train.jsonl",
                  "purpose": "search"
                },
                {
                  "id": "file-XjGxS3KTG0uNmNOK362iJua3",
                  "object": "file",
                  "bytes": 140,
                  "created_at": 1613779121,
                  "filename": "puppy.jsonl",
                  "purpose": "search"
                }
              ],
              "object": "list"
            }
        """.trimIndent()
        val result = mockValidApiRequest(response) {
            listFiles()
        }
        assertEquals("list", result.type)
    }

    @Test
    fun testExampleUploadFile() = runTest {
        val fileName = "mydata.jsonl"
        val request = UploadFileRequest(
            "{}".toByteArray(),
            fileName,
            purpose = "fine-tune"
        )
        val response = """
            {
              "id": "file-XjGxS3KTG0uNmNOK362iJua3",
              "object": "file",
              "bytes": 140,
              "created_at": 1613779121,
              "filename": "mydata.jsonl",
              "purpose": "fine-tune"
            }
        """.trimIndent()
        val result = mockValidApiRequest(response) {
            uploadFile(request)
        }
        assertEquals(fileName, result.fileName)
        assertEquals("fine-tune", result.purpose)
    }

    @Test
    fun testExampleDeleteFile() = runTest {
        val fileId = "file-XjGxS3KTG0uNmNOK362iJua3"
        val response = """
            {
              "id": "file-XjGxS3KTG0uNmNOK362iJua3",
              "object": "file",
              "deleted": true
            }
        """.trimIndent()
        val result = mockValidApiRequest(response) {
            deleteFile(fileId)
        }
        assertTrue(result.deleted)
    }

    @Test
    fun testExampleRetrieveFile() = runTest {
        val fileId = "file-XjGxS3KTG0uNmNOK362iJua3"
        val response = """
            {
              "id": "file-XjGxS3KTG0uNmNOK362iJua3",
              "object": "file",
              "bytes": 140,
              "created_at": 1613779657,
              "filename": "mydata.jsonl",
              "purpose": "fine-tune"
            }
        """.trimIndent()
        val result = mockValidApiRequest(response) {
            retrieveFile(fileId)
        }
        assertEquals(fileId, result.id)
    }

    @Test
    fun testExampleRetrieveFileContent() = runTest {
        val fileId = "file-XjGxS3KTG0uNmNOK362iJua3"
        // val result = runValidCase { result.api.retrieveFileContent(fileId) }
        // TODO: test this
    }
}