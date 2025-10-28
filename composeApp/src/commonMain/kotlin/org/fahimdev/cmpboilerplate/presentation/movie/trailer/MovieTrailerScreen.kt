package org.fahimdev.cmpboilerplate.presentation.movie.trailer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.fahimdev.cmpboilerplate.core.components.PlatformWebView
import org.fahimdev.cmpboilerplate.presentation.movie.trailer.events.MovieTrailerEvents
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MovieTrailerScreen(
    movieId: Int,
    onClose: () -> Unit,
    navController: NavController,
    viewModel: MovieTrailerViewModel = koinViewModel()
) {
    val states by viewModel.states.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(MovieTrailerEvents.OnGetTrailerURL(movieId))
    }

    Box(modifier = Modifier.fillMaxSize()) {
        MovieTrailerSkeleton(url = states.url, navController = navController)
    }
}

@Composable
fun MovieTrailerSkeleton(url: String?, navController: NavController) {
    if (url == null) return
    PlatformWebView(
        url = url,
        navController = navController,
        enableJavaScript = true,
    )
}