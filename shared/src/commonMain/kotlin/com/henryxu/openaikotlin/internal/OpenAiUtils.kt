package com.henryxu.openaikotlin.internal

import com.henryxu.openaikotlin.models.OpenAiClientRequestError
import io.ktor.client.plugins.ClientRequestException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal object OpenAiUtils {

    internal fun parseOpenAiClientRequestError(e: ClientRequestException): OpenAiClientRequestError? {
        // DO NOT REMOVE BACKSLASHES
        val errorRegex = Regex("(?<=\"error\":)(.*)(?=\\})")
        val match = errorRegex.find(e.message.replace("\n", ""))
        match?.value?.let {
            return Json.decodeFromString(it)
        }
        return null
    }
}