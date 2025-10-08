package org.fahimdev.cmpboilerplate.presentation.movie.list

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.fahimdev.cmpboilerplate.domain.usecase.GetPopularMovieListUseCase
import org.fahimdev.cmpboilerplate.domain.usecase.GetTrendingMovieListUseCase
import org.fahimdev.cmpboilerplate.domain.usecase.GetUpcomingMovieListUseCase
import org.fahimdev.cmpboilerplate.presentation.base.BaseViewModel
import org.fahimdev.cmpboilerplate.presentation.movie.list.events.MovieListEvents
import org.fahimdev.cmpboilerplate.presentation.movie.list.states.MovieListStates

class MovieListViewModel(
    private val getTrendingMovieListUseCase: GetTrendingMovieListUseCase,
    private val getPopularMovieListUseCase: GetPopularMovieListUseCase,
    private val getUpcomingMovieListUseCase: GetUpcomingMovieListUseCase
) : BaseViewModel() {
    private val _states = MutableStateFlow(MovieListStates())
    val states: StateFlow<MovieListStates> = _states.asStateFlow()

    init {
        loadMovieList()
    }

    fun onEvent(event: MovieListEvents){
        when(event){
            is MovieListEvents.OnMovieClick -> {

            }

            is MovieListEvents.OnViewAllClick -> {

            }
        }
    }

    private fun loadMovieList() = viewModelScope.launch {
        _states.value = _states.value.copy(isLoading = true, error = null)

        try {
            val trendingDeferred = async { getTrendingMovieListUseCase.invoke() }
            val popularDeferred = async { getPopularMovieListUseCase.invoke() }
            val upcomingDeferred = async { getUpcomingMovieListUseCase.invoke() }

            val trending = trendingDeferred.await()
            val popular = popularDeferred.await()
            val upcoming = upcomingDeferred.await()

            _states.value = MovieListStates(
                isLoading = false,
                trendingMovies = if(trending.size > 4) trending.take(4) else trending,
                popularMovies = if(popular.size > 4) popular.take(4) else popular,
                upcomingMovies = if(upcoming.size > 4) upcoming.take(4) else upcoming
            )
        } catch (e: Exception) {
            _states.value = _states.value.copy(
                isLoading = false,
                error = e.message ?: "Unknown error"
            )
        }
    }
}