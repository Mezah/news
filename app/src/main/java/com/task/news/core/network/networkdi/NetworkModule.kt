package com.task.news.core.network.networkdi

import com.google.gson.Gson
import com.task.news.listing.data.remote.EndPoint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    fun providesLogginInterceptor() =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()

    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl(): String = "https://newsapi.org/v2/"

    @Provides
    fun provideGson() = Gson()

    @Provides
    fun provideRetrofitInstance(
        @Named("BaseUrl") baseUrl: String,
        gson: Gson,
        okHttpClient: OkHttpClient,
    ) =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    fun apiClient(retrofit: Retrofit) = retrofit.create(EndPoint::class.java)
}