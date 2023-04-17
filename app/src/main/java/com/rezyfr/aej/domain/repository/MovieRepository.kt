package com.rezyfr.aej.domain.repository

import androidx.paging.PagingData
import com.rezyfr.aej.data.model.response.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun discoverPopularMovies(): Flow<PagingData<MovieResponse>>
}