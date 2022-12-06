package com.henryxu.openaikotlin

import com.henryxu.openaikotlin.internal.ConcreteOpenAiClient

enum class Version(val value: String) {
    V1("v1")
}

interface OpenAiClient {
    val api: OpenAiApi
}
class OpenAiClientBuilder(
    val apiKey: String
) {
    internal var organization: String = ""
    internal var version: Version = Version.V1

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