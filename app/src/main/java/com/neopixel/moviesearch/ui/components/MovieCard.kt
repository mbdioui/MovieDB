package com.neopixel.moviesearch.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.neopixel.moviesearch.data.model.MovieDTO

@Composable
fun MovieCard(
    movie: MovieDTO,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MoviePoster(
                posterPath = movie.posterPath,
                title = movie.title,
                modifier = Modifier
                    .width(80.dp)
                    .height(120.dp)
            )
            MovieInfo(
                movie = movie,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
} 