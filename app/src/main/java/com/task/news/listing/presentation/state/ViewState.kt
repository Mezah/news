package com.task.news.listing.presentation.state

import com.task.news.listing.domain.model.HeadLine

data class ViewState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val data: List<HeadLine> = emptyList(),
    val errorMessage:String = ""
)