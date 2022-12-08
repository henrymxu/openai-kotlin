package com.henryxu.openaikotlin

import com.henryxu.openaikotlin.internal.ConcreteOpenAiClient
import io.ktor.client.engine.HttpClientEngine

enum class Version(val value: String) {
    V1("v1")
}

interface OpenAiClient {
    val api: OpenAiApi
}
class OpenAiClientBuilder(
    internal val apiKey: String,
) {
    internal var httpClientEngine: HttpClientEngine? = null
    internal var organization: String = ""
    internal var version: Version = Version.V1

    fun setHttpClientEngine(httpClientEngine: HttpClientEngine): OpenAiClientBuilder {
        this.httpClientEngine = httpClientEngine
        return this
    }

    fun addOrganization(organization: String): OpenAiClientBuilder {
        this.organization = organization
        return this
    }

    fun changeVersion(version: Version): OpenAiClientBuilder {
        this.version = version
        return this
    }

    fun build(): OpenAiClient = ConcreteOpenAiClient(this)
}