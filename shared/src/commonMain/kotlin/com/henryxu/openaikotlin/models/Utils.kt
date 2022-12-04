package com.henryxu.openaikotlin.models

import io.ktor.client.request.forms.*
import io.ktor.http.*

object Utils {
    fun FormBuilder.appendImage(key: String, image: ByteArray, fileName: String = "random.png") {
        append(key, image, Headers.build {
            append(HttpHeaders.ContentType, ContentType.Application.OctetStream)
            append(HttpHeaders.ContentDisposition, "filename=\"$fileName\"")
        })
    }

    fun FormBuilder.appendFile(key: String, file: ByteArray, fileName: String = "random.json") {
        append(key, file, Headers.build {
            append(HttpHeaders.ContentType, ContentType.Application.OctetStream)
            append(HttpHeaders.ContentDisposition, "filename=\"$fileName\"")
        })
    }

    fun FormBuilder.appendIfNotNull(key: String, value: String?) {
        value?.let { append(key, it) }
    }

    fun FormBuilder.appendIfNotNull(key: String, value: Number?) {
        value?.let { append(key, it) }
    }

    fun FormBuilder.appendIfNotNull(key: String, value: ByteArray?) {
        value?.let { append(key, it) }
    }
}