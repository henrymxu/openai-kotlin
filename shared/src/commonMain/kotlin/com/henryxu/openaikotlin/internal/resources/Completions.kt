package com.henryxu.openaikotlin.internal.resources

import io.ktor.resources.Resource

@kotlinx.serialization.Serializable
@Resource("/completions")
internal class CompletionsResource
