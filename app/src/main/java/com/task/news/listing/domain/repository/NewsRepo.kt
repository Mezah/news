package com.task.news.listing.domain.repository

import com.task.news.listing.domain.model.HeadLine

interface NewsRepo {

    suspend fun loadHeadLines(page: Int, query: String): Result<List<HeadLine>>
}