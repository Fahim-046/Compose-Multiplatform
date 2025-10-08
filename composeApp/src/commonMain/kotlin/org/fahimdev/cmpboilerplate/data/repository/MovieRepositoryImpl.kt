package org.fahimdev.cmpboilerplate.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.fahimdev.cmpboilerplate.data.dataSource.remote.apiService.MovieApiService
import org.fahimdev.cmpboilerplate.data.mapper.MovieMapper
import org.fahimdev.cmpboilerplate.domain.model.Movie
import org.fahimdev.cmpboilerplate.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val movieApiService: MovieApiService
) : MovieRepository {
    override suspend fun getTrendingMovies(): List<Movie?> = withContext(Dispatchers.IO) {
        try {
            val response = movieApiService.getTrendingMovies()
            response.results.map(MovieMapper::mapResponseToDomain)
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getUpcomingMovies(): List<Movie?> = withContext(Dispatchers.IO) {
        try {
            val response = movieApiService.getUpcomingMovies()
            response.results.map(MovieMapper::mapResponseToDomain)
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getPopularMovies(): List<Movie?> {
        return try {
            val response = movieApiService.getPopularMovies()
            response.results.map(MovieMapper::mapResponseToDomain)
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getMoviesByCategory(category: String, page: Int): List<Movie?> =
        withContext(Dispatchers.IO) {
            try {
                val response = when (category) {
                    "Popular" -> movieApiService.getPopularMovies(page)
                    "Upcoming" -> movieApiService.getUpcomingMovies(page)
                    "Trending" -> movieApiService.getTrendingMovies(page)
                    else -> throw IllegalArgumentException("Invalid category: $category")
                }
                response.results.map(MovieMapper::mapResponseToDomain)
            } catch (e: Exception) {
                emptyList()
            }
        }

    override suspend fun getMovieDetails(id: Int): Movie? = withContext(Dispatchers.IO) {
        val movie = movieApiService.getMovieDetails(id)
        MovieMapper.mapResponseToDomain(movie)
    }


    override suspend fun getMovieById(id: Int): Movie {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieByPage(page: Int): List<Movie> {
        TODO("Not yet implemented")
    }

    override suspend fun searchMovie(query: String): List<Movie> {
        TODO("Not yet implemented")
    }

}