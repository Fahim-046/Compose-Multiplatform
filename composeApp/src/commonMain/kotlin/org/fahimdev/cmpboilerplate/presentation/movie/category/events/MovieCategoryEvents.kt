package org.fahimdev.cmpboilerplate.presentation.movie.category.events

sealed class MovieCategoryEvents{
    data class GetMoviesByCategory(val type: String): MovieCategoryEvents()
}