package com.task.news.listing.data.remote

import com.task.news.listing.data.remote.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface EndPoint {

    @GET("top-headlines")
    suspend fun topHeadLines(
        @Query("country") country: String,
        @Query("page") page: Int,
        @Query("q") query: String? = null,
        @Header("x-api-key") header: String = "7ec2c3c1d37a437b8d214f8664582530",
    ): Response<ApiResponse>

}