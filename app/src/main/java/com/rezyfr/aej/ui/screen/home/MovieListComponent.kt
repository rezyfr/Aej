package com.rezyfr.aej.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.rezyfr.aej.BuildConfig
import com.rezyfr.aej.data.model.response.MovieResponse

@Composable
fun MovieListItem(movie: MovieResponse) {
    Column(
        modifier = Modifier
            .padding(top = 8.dp, start = 16.dp)
            .width(139.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        MovieListImage(imgPath = movie.posterPath.orEmpty())
        MovieListDetail(modifier = Modifier.align(Alignment.Start), movie = movie)
    }
}

@Composable
fun MovieListImage(imgPath: String) {
    Image(
        modifier = Modifier
            .height(210.dp)
            .width(139.dp)
            .clip(RoundedCornerShape(8.dp)),
        painter = rememberAsyncImagePainter(
            model = BuildConfig.IMAGE_URL + imgPath,
        ),
        contentDescription = "Poster"
    )
}

@Composable
fun MovieListDetail(modifier: Modifier = Modifier, movie: MovieResponse) {
    val lineHeight = MaterialTheme.typography.bodyMedium.fontSize
    Text(
        text = movie.title.orEmpty(),
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
            .padding(PaddingValues(top = 8.dp))
            .sizeIn(minHeight = with(LocalDensity.current) {
                (lineHeight * 3).toDp()
            }),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
    )
    Text(
        text = "${movie.voteCount} Reviews",
        style = MaterialTheme.typography.bodyMedium,
    )
}