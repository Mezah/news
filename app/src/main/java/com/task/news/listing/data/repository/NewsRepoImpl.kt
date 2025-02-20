package com.task.news.listing.data.repository

import com.task.news.listing.data.remote.RemoteDataSource
import com.task.news.listing.domain.model.EmptyListException
import com.task.news.listing.domain.model.HeadLine
import com.task.news.listing.domain.repository.NewsRepo
import javax.inject.Inject

internal class NewsRepoImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : NewsRepo {
    override suspend fun loadHeadLines(page: Int,query:String): Result<List<HeadLine>> {
        return try {
            val result = remoteDataSource.loadTopHeadLine(page,query)
            if (result.isSuccessful)
                Result.success(result.body()?.articles?.map {
                    HeadLine(
                        it.title,
                        it.urlToImage,
                        it.content
                    )
                }
                    ?: emptyList())
            else {
                // parse error body
                Result.failure(EmptyListException(/* message from parsed error body*/))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }

    }
}