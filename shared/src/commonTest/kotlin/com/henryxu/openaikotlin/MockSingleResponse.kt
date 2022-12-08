package com.henryxu.openaikotlin

import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf

data class MockSingleResponse(
    val content: String,
    val status: HttpStatusCode,
    val headers: Headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString()),
)
