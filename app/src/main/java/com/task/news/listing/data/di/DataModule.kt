package com.task.news.listing.data.di

import com.task.news.listing.data.repository.NewsRepoImpl
import com.task.news.listing.domain.repository.NewsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Binds
    abstract fun bindRepoInstance(
        repoImpl: NewsRepoImpl
    ): NewsRepo
}