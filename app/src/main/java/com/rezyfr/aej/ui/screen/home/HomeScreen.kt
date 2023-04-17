package com.rezyfr.aej.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.rezyfr.aej.data.model.response.MovieResponse

@Composable
fun Home(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val popularMovies = viewModel.getPopularMovies().collectAsLazyPagingItems()
    HomeScreen(movies = popularMovies, modifier = modifier)
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    movies: LazyPagingItems<MovieResponse>,
) {
    when (movies.loadState.refresh) {
        is LoadState.Loading -> {
            // Show loading
        }

        is LoadState.Error -> {
            // Show error
        }

        else -> {
            HomeContent(movies = movies)
        }
    }
}

@Composable
fun HomeContent(modifier: Modifier = Modifier, movies: LazyPagingItems<MovieResponse>) {
    Column(Modifier.padding(top = 16.dp)) {
        Text(
            text = "Popular Movies",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 16.dp)
        )
        LazyRow(modifier) {
            items(movies) { movie ->
                movie?.let {
                    MovieListItem(movie)
                }
            }
        }
    }
}