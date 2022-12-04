package com.henryxu.openaikotlin

import com.henryxu.openaikotlin.services.CompletionsApi
import com.henryxu.openaikotlin.services.EditsApi
import com.henryxu.openaikotlin.services.EmbeddingsApi
import com.henryxu.openaikotlin.services.EnginesApi
import com.henryxu.openaikotlin.services.FilesApi
import com.henryxu.openaikotlin.services.FineTunesApi
import com.henryxu.openaikotlin.services.ImagesApi
import com.henryxu.openaikotlin.services.ModelsApi
import com.henryxu.openaikotlin.services.ModerationsApi

interface OpenAiApi : ModelsApi, CompletionsApi, EditsApi, ImagesApi, EmbeddingsApi, FilesApi,
    FineTunesApi, ModerationsApi, EnginesApi

data class Response<T>(
    val result: T?,
    val raw: String?,
    val error: Error?,
)