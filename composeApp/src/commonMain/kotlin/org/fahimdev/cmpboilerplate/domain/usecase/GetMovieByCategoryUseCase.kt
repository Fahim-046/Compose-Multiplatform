package org.fahimdev.cmpboilerplate.domain.usecase

import org.fahimdev.cmpboilerplate.domain.repository.MovieRepository

class GetMovieByCategoryUseCase(private val movieRepository: MovieRepository) {
    suspend fun invoke(category: String, page:Int = 1) = movieRepository.getMoviesByCategory(category, page)
}