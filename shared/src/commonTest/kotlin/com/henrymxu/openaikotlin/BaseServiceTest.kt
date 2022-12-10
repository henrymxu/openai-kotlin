package com.henrymxu.openaikotlin

import com.henrymxu.openaikotlin.models.OpenAiClientRequestError
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.resetMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertNotNull
import kotlin.test.assertNull

abstract class BaseServiceTest {
    @BeforeTest
    fun setup() {
    }

    companion object {
        private val DEFAULT_HEADERS = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        private val STREAM_HEADERS = headersOf(HttpHeaders.ContentType, ContentType.Application.OctetStream.toString())

        suspend fun <T> mockValidApiRequest(response: String, fn: suspend OpenAiApi.() -> Response<T>): T {
            return mockApiRequest(
                MockSingleResponse(response, HttpStatusCode.OK, DEFAULT_HEADERS),
                fn
            ).assertValidRequest()
        }

        suspend fun <T> mockInvalidApiRequest(
            response: String,
            fn: suspend OpenAiApi.() -> Response<T>
        ): OpenAiClientRequestError {
            return mockApiRequest(
                MockSingleResponse(response, HttpStatusCode.BadRequest, DEFAULT_HEADERS),
                fn
            ).assertInvalidRequest()
        }

        suspend fun <T> mockStreamApiRequest(
            responses: List<String>,
            fn: suspend OpenAiApi.() -> Flow<Response<T>>
        ): List<Response<T>> {
            return mockRequest(MockEngine {
                respond(responses.joinToString("\n"), HttpStatusCode.OK, STREAM_HEADERS)
            }, fn).toList()
        }

        private suspend fun <T> mockApiRequest(response: MockSingleResponse, fn: suspend OpenAiApi.() -> T): T {
            return mockRequest(MockEngine {
                respond(response.content, response.status, response.headers)
            }, fn)
        }

        private suspend fun <T> mockRequest(mockEngine: MockEngine, fn: suspend OpenAiApi.() -> T): T {
            return OpenAiClientBuilder("INVALID").setHttpClientEngine(mockEngine).build().api.fn()
        }

        private fun <T> Response<T>.assertValidRequest(): T {
            assertNull(error)
            assertNotNull(result)
            return result!!
        }

        private fun <T> Response<T>.assertInvalidRequest(): OpenAiClientRequestError {
            assertNull(result)
            assertNotNull(error)
            return error!!
        }
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
    }
}