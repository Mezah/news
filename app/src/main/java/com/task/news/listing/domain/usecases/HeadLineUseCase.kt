package com.task.news.listing.domain.usecases

import com.task.news.listing.domain.model.HeadLine
import com.task.news.listing.domain.repository.NewsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HeadLineUseCase @Inject constructor(private val newsRepo: NewsRepo) {

    fun topHeadLines(page: Int, query: String): Flow<Result<List<HeadLine>>> = flow {

        val result = newsRepo.loadHeadLines(page, query)
        emit(result)
    }
}