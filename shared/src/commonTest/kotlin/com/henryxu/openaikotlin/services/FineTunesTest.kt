package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.BaseServiceTest
import com.henryxu.openaikotlin.models.CreateFineTuneRequest
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class FineTunesTest: BaseServiceTest() {
    @Test
    fun testExampleCreateFineTune() = runTest {
        val request = CreateFineTuneRequest("file-XGinujblHPwGLSztz8cPS8XY")
        val response = """
            {
              "id": "ft-AF1WoRqd3aJAHsqc9NY7iL8F",
              "object": "fine-tune",
              "model": "curie",
              "created_at": 1614807352,
              "events": [
                {
                  "object": "fine-tune-event",
                  "created_at": 1614807352,
                  "level": "info",
                  "message": "Job enqueued. Waiting for jobs ahead to complete. Queue number: 0."
                }
              ],
              "fine_tuned_model": null,
              "hyperparams": {
                "batch_size": 4,
                "learning_rate_multiplier": 0.1,
                "n_epochs": 4,
                "prompt_loss_weight": 0.1
              },
              "organization_id": "org-...",
              "result_files": [],
              "status": "pending",
              "validation_files": [],
              "training_files": [
                {
                  "id": "file-XGinujblHPwGLSztz8cPS8XY",
                  "object": "file",
                  "bytes": 1547276,
                  "created_at": 1610062281,
                  "filename": "my-data-train.jsonl",
                  "purpose": "fine-tune-train"
                }
              ],
              "updated_at": 1614807352
            }
        """.trimIndent()
        val result = mockValidApiRequest(response) {
            createFineTune(request)
        }
        assertTrue { result.id.startsWith("ft-") }
    }

    @Test
    fun testExampleListFineTunes() = runTest {
        val response = """
            {
              "object": "list",
              "data": [
                {
                  "id": "ft-AF1WoRqd3aJAHsqc9NY7iL8F",
                  "object": "fine-tune",
                  "model": "curie",
                  "created_at": 1614807352,
                  "fine_tuned_model": null,
                  "hyperparams": {
                    "batch_size": 4,
                    "learning_rate_multiplier": 0.1,
                    "n_epochs": 4,
                    "prompt_loss_weight": 0.1
                  },
                  "organization_id": "org-...",
                  "result_files": [],
                  "status": "pending",
                  "validation_files": [],
                  "training_files": [],
                  "updated_at": 1614807352
                }
              ]
            }
        """.trimIndent()
        val result = mockValidApiRequest(response) {
            listFineTunes()
        }
        assertEquals("list", result.type)
    }

    @Test
    fun testExampleRetrieveFineTune() = runTest {
        val fineTuneId = "ft-AF1WoRqd3aJAHsqc9NY7iL8F"
        val response = """
            {
              "id": "ft-AF1WoRqd3aJAHsqc9NY7iL8F",
              "object": "fine-tune",
              "model": "curie",
              "created_at": 1614807352,
              "events": [
                {
                  "object": "fine-tune-event",
                  "created_at": 1614807352,
                  "level": "info",
                  "message": "Job enqueued. Waiting for jobs ahead to complete. Queue number: 0."
                },
                {
                  "object": "fine-tune-event",
                  "created_at": 1614807356,
                  "level": "info",
                  "message": "Job started."
                },
                {
                  "object": "fine-tune-event",
                  "created_at": 1614807861,
                  "level": "info",
                  "message": "Uploaded snapshot: curie:ft-acmeco-2021-03-03-21-44-20."
                },
                {
                  "object": "fine-tune-event",
                  "created_at": 1614807864,
                  "level": "info",
                  "message": "Uploaded result files: file-QQm6ZpqdNwAaVC3aSz5sWwLT."
                },
                {
                  "object": "fine-tune-event",
                  "created_at": 1614807864,
                  "level": "info",
                  "message": "Job succeeded."
                }
              ],
              "fine_tuned_model": "curie:ft-acmeco-2021-03-03-21-44-20",
              "hyperparams": {
                "batch_size": 4,
                "learning_rate_multiplier": 0.1,
                "n_epochs": 4,
                "prompt_loss_weight": 0.1
              },
              "organization_id": "org-...",
              "result_files": [
                {
                  "id": "file-QQm6ZpqdNwAaVC3aSz5sWwLT",
                  "object": "file",
                  "bytes": 81509,
                  "created_at": 1614807863,
                  "filename": "compiled_results.csv",
                  "purpose": "fine-tune-results"
                }
              ],
              "status": "succeeded",
              "validation_files": [],
              "training_files": [
                {
                  "id": "file-XGinujblHPwGLSztz8cPS8XY",
                  "object": "file",
                  "bytes": 1547276,
                  "created_at": 1610062281,
                  "filename": "my-data-train.jsonl",
                  "purpose": "fine-tune-train"
                }
              ],
              "updated_at": 1614807865
            }
        """.trimIndent()
        val result = mockValidApiRequest(response) {
            retrieveFineTune(fineTuneId)
        }
        assertEquals(fineTuneId, result.id)
    }

    @Test
    fun testExampleCancelFineTune() = runTest {
        val fineTuneId = "ft-xhrpBbvVUzYGo8oUO1FY4nI7"
        val response = """
            {
              "id": "ft-xhrpBbvVUzYGo8oUO1FY4nI7",
              "object": "fine-tune",
              "model": "curie",
              "created_at": 1614807770,
              "events": [],
              "fine_tuned_model": null,
              "hyperparams": {
                "batch_size": 4,
                "learning_rate_multiplier": 0.1,
                "n_epochs": 4,
                "prompt_loss_weight": 0.1
              },
              "organization_id": "org-...",
              "result_files": [],
              "status": "cancelled",
              "validation_files": [],
              "training_files": [
                {
                  "id": "file-XGinujblHPwGLSztz8cPS8XY",
                  "object": "file",
                  "bytes": 1547276,
                  "created_at": 1610062281,
                  "filename": "my-data-train.jsonl",
                  "purpose": "fine-tune-train"
                }
              ],
              "updated_at": 1614807789
            }
        """.trimIndent()
        val result = mockValidApiRequest(response) {
            cancelFineTune(fineTuneId)
        }
        assertEquals(fineTuneId, result.id)
        assertEquals("cancelled", result.status)
    }

    @Test
    fun testExampleListFineTuneEvents() = runTest {
        val fineTuneId = "ft-AF1WoRqd3aJAHsqc9NY7iL8F"
        val response = """
            {
              "object": "list",
              "data": [
                {
                  "object": "fine-tune-event",
                  "created_at": 1614807352,
                  "level": "info",
                  "message": "Job enqueued. Waiting for jobs ahead to complete. Queue number: 0."
                },
                {
                  "object": "fine-tune-event",
                  "created_at": 1614807356,
                  "level": "info",
                  "message": "Job started."
                },
                {
                  "object": "fine-tune-event",
                  "created_at": 1614807861,
                  "level": "info",
                  "message": "Uploaded snapshot: curie:ft-acmeco-2021-03-03-21-44-20."
                },
                {
                  "object": "fine-tune-event",
                  "created_at": 1614807864,
                  "level": "info",
                  "message": "Uploaded result files: file-QQm6ZpqdNwAaVC3aSz5sWwLT."
                },
                {
                  "object": "fine-tune-event",
                  "created_at": 1614807864,
                  "level": "info",
                  "message": "Job succeeded."
                }
              ]
            }
        """.trimIndent()
        val result = mockValidApiRequest(response) {
            listFineTuneEvents(fineTuneId)
        }
        assertEquals("list", result.type)
    }

    @Test
    fun testExampleDeleteFineTuneModel() = runTest {
        val fineTuneId = "curie:ft-acmeco-2021-03-03-21-44-20"
        val response = """
            {
              "id": "curie:ft-acmeco-2021-03-03-21-44-20",
              "object": "model",
              "deleted": true
            }
        """.trimIndent()
        val result = mockValidApiRequest(response) {
            deleteFineTuneModel(fineTuneId)
        }
        assertTrue(result.deleted)
    }

    @Test
    fun testStreamFineTuneEvents() = runTest {
        val fineTuneId = "ft-AF1WoRqd3aJAHsqc9NY7iL8F"
        // TODO: find the response for stream
        val response = listOf("[DONE]")

        val result = mockStreamApiRequest(response) {
            streamFineTuneEvents(fineTuneId)
        }
//
//        assertEquals(2, result.size)
//
//        val firstResult = result.first().result
//        assertNotNull(firstResult)
    }
}
