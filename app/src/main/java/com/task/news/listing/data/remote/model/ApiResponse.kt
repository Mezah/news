package com.task.news.listing.data.remote.model


data class ApiResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)

data class Article(
    val title: String,
    val urlToImage: String?,
    val content: String?
)

data class ErrorResponse(
    val status: String,
    val code: String,
    val message: String,
)