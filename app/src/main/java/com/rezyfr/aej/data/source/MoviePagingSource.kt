package com.rezyfr.aej.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rezyfr.aej.core.network.response.handleResponse
import com.rezyfr.aej.data.model.response.MovieResponse
import com.rezyfr.aej.data.service.MovieService

class MoviePagingSource(
    private val service: MovieService
) : PagingSource<Int, MovieResponse>() {
    override fun getRefreshKey(state: PagingState<Int, MovieResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponse> {
        return try {
            val page = params.key ?: 1
            val response = service.discoverMovie(page = page).handleResponse()
            LoadResult.Page(
                data = response.results.orEmpty(),
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.results?.isEmpty() == true) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}