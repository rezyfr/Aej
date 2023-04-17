package com.rezyfr.aej.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rezyfr.aej.data.model.response.MovieResponse
import com.rezyfr.aej.data.source.MoviePagingSource
import com.rezyfr.aej.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val pagingSource: MoviePagingSource
) : MovieRepository {
    override fun discoverPopularMovies(): Flow<PagingData<MovieResponse>> =
        Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { pagingSource }
        ).flow
}