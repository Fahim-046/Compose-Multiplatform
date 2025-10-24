package org.fahimdev.cmpboilerplate.presentation.movie.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.fahimdev.cmpboilerplate.core.components.topbar.PrimaryTopBar
import org.fahimdev.cmpboilerplate.core.enums.toLocalizedTitle
import org.fahimdev.cmpboilerplate.domain.model.Movie
import org.fahimdev.cmpboilerplate.presentation.base.BaseScreen
import org.fahimdev.cmpboilerplate.presentation.movie.category.events.MovieCategoryEvents
import org.fahimdev.cmpboilerplate.presentation.movie.list.components.MovieTile
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MovieCategoryScreen(
    modifier: Modifier = Modifier,
    type: String,
    onNavigateBack: () -> Unit,
    viewModel: MovieCategoryViewModel = koinViewModel()
) {
    LaunchedEffect(key1 = type){
        viewModel.onEvent(MovieCategoryEvents.GetMoviesByCategory(type))
    }
    val states by viewModel.states.collectAsState()

    MovieCategorySkeleton(
        modifier = modifier,
        type = type,
        movies = states.movies,
        onNavigateBack = onNavigateBack
    )
}

@Composable
fun MovieCategorySkeleton(
    modifier: Modifier = Modifier,
    type: String,
    movies: List<Movie?>,
    onNavigateBack: () -> Unit,
) {
    BaseScreen(title = type.toLocalizedTitle(), showTopBar = true, showBackArrow = true, topBar = {
        PrimaryTopBar(
            title = type.toLocalizedTitle(),
            leadingIcon = Icons.AutoMirrored.Filled.ArrowBack,
            onLeadingIconClick = onNavigateBack,
        )
    }) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(movies.size) { index ->
                    val movie = movies[index]
                    movie.let {
                        MovieTile(movie = it)
                    }
                }
            }
        }
    }
}
