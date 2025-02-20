package com.task.news.listing.domain.model

import kotlinx.serialization.Serializable

class EmptyListException : Exception()

@Serializable
data class HeadLine(val title: String, val image: String?, val details: String?)