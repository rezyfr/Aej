package com.rezyfr.aej.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.rezyfr.aej.data.model.response.MovieResponse
import com.rezyfr.aej.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    fun getPopularMovies(): Flow<PagingData<MovieResponse>> = movieRepository.discoverPopularMovies()
}