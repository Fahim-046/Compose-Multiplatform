package org.fahimdev.cmpboilerplate.presentation.navigation

sealed class Screen(val route: String) {
    data object Authentication : Screen("auth")
    data object Settings : Screen("settings")
    data object MovieList : Screen("movie/list")
    data object MovieDetails : Screen("movie/details/{movieId}") {
        fun createRoute(movieId: Int) = "movie/details/$movieId"
    }
    data object MovieCategory : Screen("movie/category/{category}") {
        fun createRoute(category: String) = "movie/category/$category"
    }
}