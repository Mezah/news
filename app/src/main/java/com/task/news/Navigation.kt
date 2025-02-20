package com.task.news

import com.task.news.listing.domain.model.HeadLine
import kotlinx.serialization.Serializable

object Navigation {
    @Serializable
    object List

    @Serializable
    data class Details(val image: String, val details: String)
}