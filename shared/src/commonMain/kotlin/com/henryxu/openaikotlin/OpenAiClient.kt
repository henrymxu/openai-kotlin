package com.henryxu.openaikotlin

import com.henryxu.openaikotlin.internal.ConcreteOpenAiClient
import io.ktor.client.HttpClient

interface OpenAiClient {
    val api: OpenAiApi

    companion object {
        inline fun build(token: String, block: Builder.() -> Unit = { }) = Builder(token).apply(block).build()
    }

    class Builder(
        val token: String
    ) {
        internal var organization: String = ""
        internal var version: Version = Version.V1

        fun build() = ConcreteOpenAiClient(this)
    }

    enum class Version(val value: String) {
        V1("v1")
    }
}