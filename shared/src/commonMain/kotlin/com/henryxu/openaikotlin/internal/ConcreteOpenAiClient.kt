package com.henryxu.openaikotlin.internal

import com.henryxu.openaikotlin.OpenAiClient
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.resources.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class ConcreteOpenAiClient(
    private val token: String,
    version: OpenAiClient.Version,
    organization: String,
): OpenAiClient {
    override val api: ConcreteOpenAiService

    internal constructor(builder: OpenAiClient.Builder) : this(
        builder.token,
        builder.version,
        builder.organization,
    )

    init {
        val client = HttpClient {
            expectSuccess = true

            install(Auth) {
                bearer {
                    loadTokens {
                        BearerTokens(token, "")
                    }
                }
            }

            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    encodeDefaults = false
                    ignoreUnknownKeys = true
                })
            }
            install(Resources)

            defaultRequest {
                url(RequestConstants.BASE_URL + "/" + version.value + "/")
                contentType(ContentType.Application.Json)
                if (organization.isNotBlank()) {
                    header(RequestConstants.ORGANIZATION_HEADER, organization)
                }
            }
        }

        client.plugin(HttpSend).intercept { request ->
            execute(request)
            // TODO: allow hooks for intercepting
        }

        api = ConcreteOpenAiService(client = client)
    }
}