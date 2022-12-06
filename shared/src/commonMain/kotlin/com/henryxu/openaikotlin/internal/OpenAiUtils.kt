package com.henryxu.openaikotlin.internal

import com.henryxu.openaikotlin.models.OpenAiClientRequestError
import io.ktor.client.plugins.ClientRequestException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal object OpenAiUtils {

    fun parseOpenAiClientRequestError(e: ClientRequestException): OpenAiClientRequestError? {
        return parseOpenAiClientRequestError(e.message)
    }
    internal fun parseOpenAiClientRequestError(message: String): OpenAiClientRequestError? {
        val errorRegex = Regex(".*\"error\":(.*)\\}.*")
        val match = errorRegex.find(message.replace("\n", ""))
        match?.groupValues?.last()?.let {
            return Json.decodeFromString(it)
        }
        return null
    }
}