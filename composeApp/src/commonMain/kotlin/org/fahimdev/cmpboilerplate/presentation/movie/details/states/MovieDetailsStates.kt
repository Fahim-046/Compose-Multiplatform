package org.fahimdev.cmpboilerplate.presentation.movie.details.states

import org.fahimdev.cmpboilerplate.domain.model.Movie

data class MovieDetailsStates(
    val movie: Movie? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
