package com.task.news.listing.presentation.viewcomponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.task.news.R
import com.task.news.listing.domain.model.HeadLine

@Composable
fun HeadLineCard(headLine: HeadLine, onClick: (HeadLine) -> Unit) {
    ElevatedCard(modifier = Modifier
        .padding(16.dp)
        .clickable {
            onClick(headLine)
        }) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row {
                AsyncImage(
                    modifier = Modifier
                        .size(100.dp)
                        .padding(end = 16.dp),
                    model = headLine.image,
                    contentDescription = null,
                    error = painterResource(R.drawable.ic_launcher_background)
                )
                Text(text = headLine.title)
            }
        }
    }

}
