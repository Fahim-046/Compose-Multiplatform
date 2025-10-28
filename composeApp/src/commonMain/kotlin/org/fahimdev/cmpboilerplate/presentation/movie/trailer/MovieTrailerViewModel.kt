package org.fahimdev.cmpboilerplate.presentation.movie.trailer

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.fahimdev.cmpboilerplate.domain.usecase.GetMovieTrailerURLUseCase
import org.fahimdev.cmpboilerplate.presentation.base.BaseViewModel
import org.fahimdev.cmpboilerplate.presentation.movie.trailer.events.MovieTrailerEvents
import org.fahimdev.cmpboilerplate.presentation.movie.trailer.states.MovieTrailerStates

class MovieTrailerViewModel(private val movieTrailerURLUseCase: GetMovieTrailerURLUseCase) :
    BaseViewModel() {
    private val _states = MutableStateFlow(MovieTrailerStates())
    val states = _states.asStateFlow()

    fun onEvent(event: MovieTrailerEvents) {
        when (event) {
            is MovieTrailerEvents.OnGetTrailerURL -> getMovieTrailerURL(event.id)
            is MovieTrailerEvents.OnClose -> {}
        }
    }

    private fun getMovieTrailerURL(id: Int) = viewModelScope.launch {
        _states.value = _states.value.copy(isLoading = true)
        val key = movieTrailerURLUseCase.invoke(id)
        if(key != null){
            val url = "https://www.youtube.com/embed/$key"
            _states.value = _states.value.copy(isLoading = false, key = key, url = url)
        }
    }
}