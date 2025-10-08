package org.fahimdev.cmpboilerplate.presentation.movie.category.states

import org.fahimdev.cmpboilerplate.domain.model.Movie

data class CategoryStates(
    var isLoading: Boolean = false,
    var movies: List<Movie?> = emptyList(),
)