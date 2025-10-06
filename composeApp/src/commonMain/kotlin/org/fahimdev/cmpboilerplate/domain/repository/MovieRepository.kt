package org.fahimdev.cmpboilerplate.domain.repository

import org.fahimdev.cmpboilerplate.domain.model.Movie

interface MovieRepository {
    suspend fun getTrendingMovies(): List<Movie?>
    suspend fun getUpcomingMovies(): List<Movie?>
    suspend fun getPopularMovies(): List<Movie?>
    suspend fun getMoviesByCategory(category: String, page: Int): List<Movie?>
    suspend fun getMovieDetails(id: Int): Movie?
    suspend fun getMovieById(id: Int): Movie
    suspend fun getMovieByPage(page: Int): List<Movie>
    suspend fun searchMovie(query: String): List<Movie>
}