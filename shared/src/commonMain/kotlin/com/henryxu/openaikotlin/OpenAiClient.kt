package com.henryxu.openaikotlin

import com.henryxu.openaikotlin.internal.ConcreteOpenAiClient

interface OpenAiClient {
    val api: OpenAiApi

    companion object {
        inline fun build(apiKey: String, block: Builder.() -> Unit = { }) = Builder(apiKey).apply(block).build()
    }

    class Builder(
        val apiKey: String
    ) {
        internal var organization: String = ""
        internal var version: Version = Version.V1

        fun build(): OpenAiClient = ConcreteOpenAiClient(this)
    }

    enum class Version(val value: String) {
        V1("v1")
    }
}