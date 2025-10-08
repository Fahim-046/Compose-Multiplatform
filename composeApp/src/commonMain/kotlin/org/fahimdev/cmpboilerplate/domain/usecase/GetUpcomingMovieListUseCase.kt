package org.fahimdev.cmpboilerplate.domain.usecase

import org.fahimdev.cmpboilerplate.domain.repository.MovieRepository

class GetUpcomingMovieListUseCase(
    private val movieRepository: MovieRepository
) {
    suspend fun invoke() = movieRepository.getUpcomingMovies()
}