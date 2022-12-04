package com.henryxu.openaikotlin.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.henryxu.openaikotlin.OpenAiClient
import com.henryxu.openaikotlin.Response
import com.henryxu.openaikotlin.models.CreateImageRequest
import com.henryxu.openaikotlin.models.CreateModerationRequest
import com.henryxu.openaikotlin.models.ImageResult
import com.henryxu.openaikotlin.models.ModerationResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    private lateinit var openAiClient: OpenAiClient

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openAiClient = OpenAiClient.build(BuildConfig.API_KEY) {

        }
        setContent {
            MaterialTheme {
                Scaffold(
                    topBar = { TopAppBar(title = { Text(text = "OpenAI Kotlin Sample App") }) }
                ) { contentPadding ->
                    Surface(
                        modifier = Modifier.padding(contentPadding),
                        color = MaterialTheme.colorScheme.background)
                    {
                        MainContent(openAiClient)
                    }
                }
            }
        }
    }
}

@Composable
fun MainContent(openAiClient: OpenAiClient) {
    var results by rememberSaveable { mutableStateOf<ImageResult?>(null) }
    val coroutineScope = rememberCoroutineScope()
    var loading by rememberSaveable { mutableStateOf(false) }

    var errorMessage by rememberSaveable { mutableStateOf("") }

    suspend fun makeModerationRequest(prompt: String): Response<ModerationResult> {
        val request = CreateModerationRequest(input = prompt)
        val response = openAiClient.api.createModeration(request)
        loading = false
        return response
    }

    suspend fun makeImageRequest(prompt: String): Response<ImageResult> {
        val request = CreateImageRequest(
            prompt = prompt,
            n = 2,
        )
        val response = openAiClient.api.createImage(request)
        loading = false
        return response
    }

    val inappropriateContent = stringResource(R.string.error_inappropriate_content)
    val moderationRequestFailed = stringResource(R.string.error_moderation_request_failed)
    val imageRequestFailed = stringResource(R.string.error_image_request_failed)

    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImagePromptContent(errorMessage, { errorMessage = it }) {
            coroutineScope.launch {
                withContext(Dispatchers.IO) {
                    loading = true
                    val moderation = makeModerationRequest(it)
                    moderation.result?.let {
                        if (it.results.firstOrNull()?.flagged == true) {
                            errorMessage = inappropriateContent
                            return@withContext
                        }
                    } ?: run {
                        errorMessage = moderationRequestFailed + "\n" + moderation.error?.localizedMessage
                        return@withContext
                    }
                    loading = true
                    val response = makeImageRequest(it)
                    response.result?.let {
                        results = it
                    } ?: run {
                        errorMessage = imageRequestFailed + "\n" + response.error?.localizedMessage
                    }
                }
            }
        }
        val spacer = Modifier.height(8.dp).padding(vertical = 4.dp)
        if (loading) {
            LinearProgressIndicator(modifier = spacer)
        } else {
            Spacer(modifier = spacer)
        }
        ImageResultScreen(results?.data?.mapNotNull { it.url } ?: listOf())
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {

    }
}