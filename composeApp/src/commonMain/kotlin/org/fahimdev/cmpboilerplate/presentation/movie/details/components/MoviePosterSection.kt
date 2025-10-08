package org.fahimdev.cmpboilerplate.presentation.movie.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage

@Composable
fun MoviePosterSection(modifier: Modifier = Modifier, movieTitle: String, moviePosterUrl: String) {
    val gradientBrush = remember {
        Brush.verticalGradient(
            colors = listOf(
                Color.Transparent,
                Color.Black.copy(alpha = 0.3f),
                Color.Black.copy(alpha = 0.8f)
            )
        )
    }

    Box(modifier = modifier.fillMaxSize()) {
        AsyncImage(
            model = moviePosterUrl,
            contentDescription = movieTitle,
            contentScale = ContentScale.FillBounds,
            clipToBounds = true,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradientBrush)
        )
    }
}