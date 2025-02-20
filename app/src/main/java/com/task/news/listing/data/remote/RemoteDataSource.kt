package com.task.news.listing.data.remote

import com.task.news.listing.data.remote.model.ApiResponse
import retrofit2.Response
import javax.inject.Inject

internal class RemoteDataSource @Inject constructor(
    private val endPoint: EndPoint
) {

    suspend fun loadTopHeadLine(page: Int, query: String): Response<ApiResponse> {
        return endPoint.topHeadLines(country = "us", page = page, query = query)
    }

}