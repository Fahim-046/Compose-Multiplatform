package org.fahimdev.cmpboilerplate.presentation.movie.list.states
import org.fahimdev.cmpboilerplate.domain.model.Movie

data class MovieListStates(
    val isLoading: Boolean = false,
    val movies: List<Movie?> = emptyList(),
    val trendingMovies: List<Movie?> = emptyList(),
    val popularMovies: List<Movie?> = emptyList(),
    val upcomingMovies: List<Movie?> = emptyList(),
    val error: String? = null
)
