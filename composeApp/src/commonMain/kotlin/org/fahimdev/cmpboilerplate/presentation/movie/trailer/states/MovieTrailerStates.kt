package org.fahimdev.cmpboilerplate.presentation.movie.trailer.states

data class MovieTrailerStates(
    val isLoading: Boolean = false,
    val url: String? = null,
    val key: String? = null
)
