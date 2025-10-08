package org.fahimdev.cmpboilerplate.presentation.movie.list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.fahimdev.cmpboilerplate.domain.model.Movie

@Composable
fun MovieGrid(
    movies: List<Movie?>,
    onMovieClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        movies.chunked(2).forEach { rowMovies ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                rowMovies.forEach { movie ->
                    MovieTile(
                        modifier = Modifier.weight(1f),
                        movie = movie,
                        onMovieClick = { movieId ->
                            onMovieClick(movieId)
                        }
                    )
                }
                if (rowMovies.size == 1) {
                    Box(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}