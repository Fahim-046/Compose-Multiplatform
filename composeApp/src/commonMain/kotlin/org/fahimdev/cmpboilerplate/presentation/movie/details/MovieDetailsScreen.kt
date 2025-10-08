package org.fahimdev.cmpboilerplate.presentation.movie.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.fahimdev.cmpboilerplate.presentation.movie.details.components.ActionButtons
import org.fahimdev.cmpboilerplate.presentation.movie.details.components.GenresSection
import org.fahimdev.cmpboilerplate.presentation.movie.details.components.MovieInfoRow
import org.fahimdev.cmpboilerplate.presentation.movie.details.components.MoviePosterSection
import org.fahimdev.cmpboilerplate.presentation.movie.details.components.QualitySection
import org.fahimdev.cmpboilerplate.presentation.movie.details.components.SynopsisSection
import org.fahimdev.cmpboilerplate.presentation.movie.details.events.MovieDetailsEvents
import org.fahimdev.cmpboilerplate.core.components.loader.BouncingDotsLoader
import org.fahimdev.cmpboilerplate.core.components.topbar.PrimaryTopBar
import org.fahimdev.cmpboilerplate.domain.model.Movie
import org.fahimdev.cmpboilerplate.presentation.base.BaseScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MovieDetailsScreen(
    modifier: Modifier = Modifier,
    id: Int,
    onBackArrowClick: () -> Unit,
    viewModel: MovieDetailsViewModel = koinViewModel()
) {
    LaunchedEffect(key1 = id) {
        viewModel.onEvent(MovieDetailsEvents.GetMovieDetails(id))
    }
    val states by viewModel.states.collectAsState()
    MovieDetailsScreenSkeleton(
        movie = states.movie,
        isLoading = states.isLoading,
        onBackArrowClick = onBackArrowClick
    )
}

@Composable
fun MovieDetailsScreenSkeleton(
    movie: Movie?,
    isLoading: Boolean,
    onBackArrowClick: () -> Unit
) {
    BaseScreen(
        title = movie?.title ?: "",
        showTopBar = true,
        showBackArrow = true,
        showBottomNavigation = false,
        topBar = {
            PrimaryTopBar(
                title = movie?.title ?: "",
                leadingIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onLeadingIconClick = {
                    onBackArrowClick()
                })
        }
    ) {
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                BouncingDotsLoader()
            }
        } else if (movie != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                MoviePosterSection(moviePosterUrl = movie.coverImage, movieTitle = movie.title)
                Spacer(modifier = Modifier.height(24.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                ) {
                    ActionButtons()
                    Spacer(modifier = Modifier.height(24.dp))
                    MovieInfoRow(rating = movie.rating, duration = movie.runtime)
                    if (movie.genres.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(32.dp))
                        GenresSection(genres = movie.genres)
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    SynopsisSection(description = movie.summary)
                    Spacer(modifier = Modifier.height(32.dp))
                    QualitySection()
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}