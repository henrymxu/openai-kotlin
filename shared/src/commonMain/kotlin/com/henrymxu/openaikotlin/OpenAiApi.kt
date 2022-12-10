package com.henrymxu.openaikotlin

import com.henrymxu.openaikotlin.models.OpenAiClientRequestError
import com.henrymxu.openaikotlin.services.CompletionsApi
import com.henrymxu.openaikotlin.services.EditsApi
import com.henrymxu.openaikotlin.services.EmbeddingsApi
import com.henrymxu.openaikotlin.services.EnginesApi
import com.henrymxu.openaikotlin.services.FilesApi
import com.henrymxu.openaikotlin.services.FineTunesApi
import com.henrymxu.openaikotlin.services.ImagesApi
import com.henrymxu.openaikotlin.services.ModelsApi
import com.henrymxu.openaikotlin.services.ModerationsApi

interface OpenAiApi : ModelsApi, CompletionsApi, EditsApi, ImagesApi, EmbeddingsApi, FilesApi,
    FineTunesApi, ModerationsApi, EnginesApi

data class Response<T>(
    val result: T?,
    val raw: String?,
    val error: OpenAiClientRequestError?
)