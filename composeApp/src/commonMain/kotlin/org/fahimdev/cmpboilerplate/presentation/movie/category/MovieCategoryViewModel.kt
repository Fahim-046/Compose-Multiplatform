package org.fahimdev.cmpboilerplate.presentation.movie.category

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.fahimdev.cmpboilerplate.domain.usecase.GetMovieByCategoryUseCase
import org.fahimdev.cmpboilerplate.presentation.base.BaseViewModel
import org.fahimdev.cmpboilerplate.presentation.movie.category.events.MovieCategoryEvents
import org.fahimdev.cmpboilerplate.presentation.movie.category.states.CategoryStates

class MovieCategoryViewModel(
    private val getMovieByCategoryUseCase: GetMovieByCategoryUseCase
) : BaseViewModel() {
    val states = MutableStateFlow(CategoryStates())

    fun onEvent(event: MovieCategoryEvents) {
        when (event) {
            is MovieCategoryEvents.GetMoviesByCategory -> {
                states.value = states.value.copy(isLoading = true)
                getMoviesByCategory(event.type)
            }
        }
    }

    private fun getMoviesByCategory(type: String) = viewModelScope.launch {
        val result = getMovieByCategoryUseCase.invoke(type)
        states.value = states.value.copy(isLoading = false, movies = result)
    }

}