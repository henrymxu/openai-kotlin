package com.henryxu.openaikotlin.services

import com.henryxu.openaikotlin.BaseServiceTest
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class ModelsTest : BaseServiceTest() {
    @Test
    fun testExampleListModels() = runTest {
        val response = """
            {
              "data": [
                {
                  "id": "model-id-0",
                  "object": "model",
                  "created": 1649880484,
                  "owned_by": "organization-owner",
                  "permission": [
                    {
                      "id": "modelperm-7a1aiNDvZTbMmXYdxngmQ8D5",
                      "object": "model_permission",
                      "created": 1670348842,
                      "allow_create_engine": false,
                      "allow_sampling": true,
                      "allow_logprobs": true,
                      "allow_search_indices": false,
                      "allow_view": true,
                      "allow_fine_tuning": false,
                      "organization": "*",
                      "group": null,
                      "is_blocking": false
                     }
                  ],
                  "root": "model-id-0",
                  "parent": null
                },
                {
                  "id": "model-id-1",
                  "object": "model",
                  "created": 1649880484,
                  "owned_by": "organization-owner",
                  "permission": [
                    {
                      "id": "modelperm-7a1aiNDvZTbMmXYdxngmQ8D5",
                      "object": "model_permission",
                      "created": 1670348842,
                      "allow_create_engine": false,
                      "allow_sampling": true,
                      "allow_logprobs": true,
                      "allow_search_indices": false,
                      "allow_view": true,
                      "allow_fine_tuning": false,
                      "organization": "*",
                      "group": null,
                      "is_blocking": false
                     }
                  ],
                  "root": "model-id-0",
                  "parent": null
                },
                {
                  "id": "model-id-2",
                  "object": "model",
                  "created": 1649880484,
                  "owned_by": "openai",
                  "permission": [
                    {
                      "id": "modelperm-7a1aiNDvZTbMmXYdxngmQ8D5",
                      "object": "model_permission",
                      "created": 1670348842,
                      "allow_create_engine": false,
                      "allow_sampling": true,
                      "allow_logprobs": true,
                      "allow_search_indices": false,
                      "allow_view": true,
                      "allow_fine_tuning": false,
                      "organization": "*",
                      "group": null,
                      "is_blocking": false
                     }
                  ],
                  "root": "model-id-0",
                  "parent": null
                }
              ],
              "object": "list"
            }
        """.trimIndent()
        val result = mockValidApiRequest(response) {
            listModels()
        }
        assertNotEquals(0, result.data.size)
    }

    @Test
    fun testExampleRetrieveModel() = runTest {
        val model = "text-davinci-003"
        val response = """
            {
              "id": "text-davinci-003",
              "object": "model",
              "created": 1669599635,
              "owned_by": "openai-internal",
              "permission": [
                {
                  "id": "modelperm-7a1aiNDvZTbMmXYdxngmQ8D5",
                  "object": "model_permission",
                  "created": 1670348842,
                  "allow_create_engine": false,
                  "allow_sampling": true,
                  "allow_logprobs": true,
                  "allow_search_indices": false,
                  "allow_view": true,
                  "allow_fine_tuning": false,
                  "organization": "*",
                  "group": null,
                  "is_blocking": false
                }
              ],
              "root": "text-davinci-003",
              "parent": null
            }
        """.trimIndent()
        val result = mockValidApiRequest(response) {
            retrieveModel(model)
        }
        assertEquals(model, result.id)
    }
}