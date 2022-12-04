package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.BaseServiceTest
import com.henryxu.openaikotlin.models.UploadFileRequest
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FilesTest: BaseServiceTest() {
    @Test
    fun testExampleListFiles() = runTest {
        val result = runValidCase { client.api.listFiles() }
        assertEquals("list", result.type)
    }

    @Test
    fun testExampleUploadFile() = runTest {
        val fileName = "files/mydata.jsonl"
        val request = UploadFileRequest(
            openFile("./openai.png"),
            fileName,
            purpose = "fine-tune"
        )
        val result = runValidCase { client.api.uploadFile(request) }
        assertEquals(fileName, result.fileName)
        assertEquals("fine-tune", result.purpose)
    }

    @Test
    fun testExampleDeleteFile() = runTest {
        val fileId = "file-XjGxS3KTG0uNmNOK362iJua3"
        val result = runValidCase { client.api.deleteFile(fileId) }
        assertTrue(result.deleted)
    }

    @Test
    fun testExampleRetrieveFile() = runTest {
        val fileId = "file-XjGxS3KTG0uNmNOK362iJua3"
        val result = runValidCase { client.api.retrieveFile(fileId) }
        assertEquals(fileId, result.id)
    }

    @Test
    fun testExampleRetrieveFileContent() = runTest {
        val fileId = "file-XjGxS3KTG0uNmNOK362iJua3"
        val result = runValidCase { client.api.retrieveFileContent(fileId) }
        // TODO: test this
    }
}