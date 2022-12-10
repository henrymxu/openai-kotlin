package com.henrymxu.openaikotlin.android

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImagePromptContent(error: String, onErrorChange: (String) -> Unit, onPromptSubmit: (String) -> Unit) {
    var prompt by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    val errorEmptyPrompt = stringResource(R.string.error_empty_prompt)

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text("Prompt") },
        value = prompt,
        onValueChange = {
            onErrorChange("")
            prompt = it
        },
        isError = error != "",
        supportingText = {
            if (error.isNotEmpty()) {
                Text(error)
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                if (prompt.isNotEmpty()) {
                    focusManager.clearFocus()
                    onPromptSubmit(prompt)
                } else {
                    onErrorChange(errorEmptyPrompt)
                }
            }
        ),
    )
}