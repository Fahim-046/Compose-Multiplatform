package org.fahimdev.cmpboilerplate.presentation.di

import org.fahimdev.cmpboilerplate.AppViewModel
import org.fahimdev.cmpboilerplate.presentation.movie.category.MovieCategoryViewModel
import org.fahimdev.cmpboilerplate.presentation.movie.details.MovieDetailsViewModel
import org.fahimdev.cmpboilerplate.presentation.movie.list.MovieListViewModel
import org.fahimdev.cmpboilerplate.presentation.settings.SettingsViewModel
import org.koin.dsl.module

val appModule = module {
    single<MovieListViewModel> {
        MovieListViewModel(
            getTrendingMovieListUseCase = get(),
            getPopularMovieListUseCase = get(),
            getUpcomingMovieListUseCase = get()
        )
    }

    factory<MovieDetailsViewModel> {
        MovieDetailsViewModel(
            getMovieDetailsUseCase = get()
        )
    }

    single<MovieCategoryViewModel> {
        MovieCategoryViewModel(
            getMovieByCategoryUseCase = get()
        )
    }

    single<SettingsViewModel> {
        SettingsViewModel(
            dataStoreManager = get()
        )
    }

    single<AppViewModel> {
        AppViewModel(
            dataStoreManager = get()
        )
    }
}