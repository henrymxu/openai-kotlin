package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.BaseServiceTest
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class EnginesTest : BaseServiceTest() {
    @Test
    fun testExampleListEngines() = runTest {
        val response = """
            {
              "data": [
                {
                  "id": "engine-id-0",
                  "object": "engine",
                  "owner": "organization-owner",
                  "ready": true
                },
                {
                  "id": "engine-id-2",
                  "object": "engine",
                  "owner": "organization-owner",
                  "ready": true
                },
                {
                  "id": "engine-id-3",
                  "object": "engine",
                  "owner": "openai",
                  "ready": false
                }
              ],
              "object": "list"
            }
        """.trimIndent()
        val result = mockValidApiRequest(response) {
            listEngines()
        }
        assertNotEquals(0, result.data.size)
    }

    @Test
    fun testExampleRetrieveEngine() = runTest {
        val model = "text-davinci-003"
        val response = """
            {
              "id": "text-davinci-003",
              "object": "engine",
              "owner": "openai",
              "ready": true
            }
        """.trimIndent()
        val result = mockValidApiRequest(response) {
            retrieveEngine(model)
        }
        assertEquals(model, result.id)
    }
}