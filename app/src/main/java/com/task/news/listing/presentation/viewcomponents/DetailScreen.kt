package com.task.news.listing.presentation.viewcomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun DetailScreen(image: String, details: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        ElevatedCard {
            AsyncImage(
                modifier = Modifier.size(300.dp),
                model = image,
                contentDescription = null
            )
        }
        Spacer(Modifier.height(16.dp))
        Text(text = details.orEmpty())
    }
}