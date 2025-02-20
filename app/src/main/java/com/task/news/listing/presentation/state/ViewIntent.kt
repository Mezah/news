package com.task.news.listing.presentation.state

sealed class ViewIntent {
    data class LoadHeadLines(val page: Int, val query: String) : ViewIntent()
}