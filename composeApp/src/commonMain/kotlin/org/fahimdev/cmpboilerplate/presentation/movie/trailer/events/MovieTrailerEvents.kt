package org.fahimdev.cmpboilerplate.presentation.movie.trailer.events

sealed class MovieTrailerEvents{
    data class OnGetTrailerURL(val id: Int): MovieTrailerEvents()
    data object OnClose: MovieTrailerEvents()
}