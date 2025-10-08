package org.fahimdev.cmpboilerplate.presentation.movie.category

import androidx.lifecycle.viewModelScope
import org.fahimdev.cmpboilerplate.presentation.movie.category.states.CategoryStates
import org.fahimdev.cmpboilerplate.presentation.movie.list.CategoryType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.fahimdev.cmpboilerplate.domain.repository.MovieRepository
import org.fahimdev.cmpboilerplate.presentation.base.BaseViewModel

class MovieCategoryViewModel(
    private val movieRepository: MovieRepository
) : BaseViewModel() {
    val states = MutableStateFlow(CategoryStates())

    fun getMoviesPaging(type: CategoryType){

    }
}