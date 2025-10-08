package org.fahimdev.cmpboilerplate.presentation.di

import org.fahimdev.cmpboilerplate.presentation.movie.category.MovieCategoryViewModel
import org.fahimdev.cmpboilerplate.presentation.movie.details.MovieDetailsViewModel
import org.fahimdev.cmpboilerplate.presentation.movie.list.MovieListViewModel
import org.koin.dsl.module

val appModule = module {
    single<MovieListViewModel> {
        MovieListViewModel(
            getTrendingMovieListUseCase = get(),
            getPopularMovieListUseCase = get(),
            getUpcomingMovieListUseCase = get()
        )
    }

    single<MovieDetailsViewModel> {
        MovieDetailsViewModel(
            getMovieDetailsUseCase = get()
        )
    }

    single<MovieCategoryViewModel> {
        MovieCategoryViewModel(
            getMovieByCategoryUseCase = get()
        )
    }
}