package com.henryxu.openaikotlin

import com.henryxu.openaikotlin.models.OpenAiClientRequestError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertNotNull
import kotlin.test.assertNull

const val STREAM_TIMEOUT_MS = 5000L
abstract class BaseServiceTest {
    protected lateinit var client: OpenAiClient

    @BeforeTest
    fun setup() {
        client = OpenAiClientBuilder(PrivateInfo.API_KEY).build()
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
    }

    protected suspend fun <T> runValidCase(fn: suspend () -> Response<T>): T {
        val result = fn()
        assertNull(result.error)
        assertNotNull(result.result)
        return result.result!!
    }

    protected suspend fun <T> runInvalidCase(fn: suspend () -> Response<T>): OpenAiClientRequestError {
        val result = fn()
        assertNull(result.result)
        assertNotNull(result.error)
        return result.error!!
    }

    protected fun openFile(fileName: String): ByteArray {
        return readImageResource(fileName)
    }
}