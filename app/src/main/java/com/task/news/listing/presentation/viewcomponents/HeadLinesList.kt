package com.task.news.listing.presentation.viewcomponents

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.task.news.listing.domain.model.HeadLine
import com.task.news.listing.presentation.state.ViewState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeadLinesList(
    modifier: Modifier = Modifier,
    viewState: ViewState,
    onCardClick: (HeadLine) -> Unit
) {
    if (viewState.isLoading) {
        LoadingIndicator()
    } else {
        if (viewState.isError) {
            ErrorMessage(modifier, viewState.errorMessage)
        } else {
            if (viewState.data.isNotEmpty()) {
                LazyColumn(modifier) {
                    items(count = viewState.data.size) { index ->
                        with(viewState.data[index]) {
                            HeadLineCard(this, onCardClick)
                        }
                    }
                }
            } else {
                EmptyMessage()
            }
        }
    }
}