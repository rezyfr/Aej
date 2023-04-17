package com.rezyfr.aej.data.service

import com.rezyfr.aej.core.network.response.ErrorResponse
import com.rezyfr.aej.core.network.response.NetworkResponse
import com.rezyfr.aej.data.model.response.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    suspend fun discoverMovie(
        @Query("page") page: Int
    ): NetworkResponse<MovieListResponse, ErrorResponse>
}