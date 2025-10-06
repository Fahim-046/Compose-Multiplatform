package org.fahimdev.cmpboilerplate.data.di

import org.fahimdev.cmpboilerplate.data.dataSource.remote.apiClient.ApiClient
import org.fahimdev.cmpboilerplate.data.dataSource.remote.apiService.MovieApiService
import org.fahimdev.cmpboilerplate.data.repository.MovieRepositoryImpl
import org.fahimdev.cmpboilerplate.domain.repository.MovieRepository
import org.koin.dsl.module

val networkModule = module {
    single {
        ApiClient(
            baseUrl = "",
            apiKey = ""
        ).create(get())
    }

    single<MovieApiService> {
        MovieApiService(get())
    }

    single<MovieRepository> {
        MovieRepositoryImpl(get())
    }
}