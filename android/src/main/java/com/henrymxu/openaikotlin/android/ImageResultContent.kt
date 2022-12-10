package com.henrymxu.openaikotlin.android

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage

@Composable
fun ImageResultScreen(imageUrls: List<String>) {
    LazyColumn(
        modifier = Modifier.padding(bottom = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(imageUrls) { imageUrl ->
            SubcomposeAsyncImage(
                model = imageUrl,
                loading = {
                    CircularProgressIndicator()
                },
                contentDescription = null
            )
        }
    }
}