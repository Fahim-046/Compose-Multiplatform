package org.fahimdev.cmpboilerplate.domain.usecase

import org.fahimdev.cmpboilerplate.domain.repository.MovieRepository

class GetMovieDetailsUseCase(
    private val movieRepository: MovieRepository
) {
    suspend fun invoke(id: Int) = movieRepository.getMovieDetails(id)
}