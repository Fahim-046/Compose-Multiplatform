package org.fahimdev.cmpboilerplate.data.di

import org.fahimdev.cmpboilerplate.data.dataSource.remote.apiClient.ApiClient
import org.fahimdev.cmpboilerplate.data.dataSource.remote.apiService.MovieApiService
import org.fahimdev.cmpboilerplate.data.repository.MovieRepositoryImpl
import org.fahimdev.cmpboilerplate.domain.repository.MovieRepository
import org.fahimdev.cmpboilerplate.domain.usecase.GetMovieDetailsUseCase
import org.fahimdev.cmpboilerplate.domain.usecase.GetPopularMovieListUseCase
import org.fahimdev.cmpboilerplate.domain.usecase.GetTrendingMovieListUseCase
import org.fahimdev.cmpboilerplate.domain.usecase.GetUpcomingMovieListUseCase
import org.koin.dsl.module

val networkModule = module {
    single {
        ApiClient(
            baseUrl = "https://api.themoviedb.org/3/",
            apiKey = "db5d93ff881544f1df428a417958ce1c",
            engine = get()
        ).create()
    }

    single<MovieApiService> {
        MovieApiService(get())
    }

    single<MovieRepository> {
        MovieRepositoryImpl(get())
    }

    factory { GetTrendingMovieListUseCase(get()) }
    factory { GetPopularMovieListUseCase(get()) }
    factory { GetUpcomingMovieListUseCase(get()) }
    factory { GetMovieDetailsUseCase(get()) }
}