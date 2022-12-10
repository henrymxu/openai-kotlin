package com.henrymxu.openaikotlin.internal

import io.ktor.client.request.forms.MultiPartFormDataContent

interface MultiPartFormDataRequest {
    fun toMultiPartFormData(): MultiPartFormDataContent
}
