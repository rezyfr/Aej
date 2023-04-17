package com.rezyfr.aej.di

import com.rezyfr.aej.data.repository.MovieRepositoryImpl
import com.rezyfr.aej.data.service.MovieService
import com.rezyfr.aej.data.source.MoviePagingSource
import com.rezyfr.aej.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideMovieRepository(
        source: MoviePagingSource
    ): MovieRepository {
        return MovieRepositoryImpl(source)
    }

    @Singleton
    @Provides
    fun providePagingSource(
        service: MovieService
    ): MoviePagingSource {
        return MoviePagingSource(service)
    }
}