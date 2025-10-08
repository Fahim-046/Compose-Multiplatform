package org.fahimdev.cmpboilerplate.presentation.movie.details

import androidx.lifecycle.viewModelScope
import org.fahimdev.cmpboilerplate.presentation.movie.details.events.MovieDetailsEvents
import org.fahimdev.cmpboilerplate.presentation.movie.details.states.MovieDetailsStates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.fahimdev.cmpboilerplate.domain.usecase.GetMovieDetailsUseCase
import org.fahimdev.cmpboilerplate.presentation.base.BaseViewModel

class MovieDetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : BaseViewModel() {
    private val _states = MutableStateFlow(MovieDetailsStates())
    val states get() = _states

    fun onEvent(event: MovieDetailsEvents) {
        when (event) {
            is MovieDetailsEvents.GetMovieDetails -> {
                _states.value = _states.value.copy(isLoading = true, movie = null)
                getMovieDetails(event.id)
            }
        }
    }

    private fun getMovieDetails(id: Int) = viewModelScope.launch {
        val result = getMovieDetailsUseCase.invoke(id)
        if (result != null) {
            _states.value = _states.value.copy(isLoading = false, movie = result)
        }
    }
}