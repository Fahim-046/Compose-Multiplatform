package org.fahimdev.cmpboilerplate.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object Authentication : Screen

    @Serializable
    data object Settings : Screen

    @Serializable
    data object MovieList : Screen

    @Serializable
    data class MovieDetails(val movieId: Int) : Screen

    @Serializable
    data class MovieCategory(val category: String) : Screen

    @Serializable
    data class MovieTrailer(val id: Int): Screen
}