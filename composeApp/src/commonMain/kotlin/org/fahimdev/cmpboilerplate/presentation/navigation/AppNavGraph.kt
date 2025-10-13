package org.fahimdev.cmpboilerplate.presentation.navigation

import MovieListScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import org.fahimdev.cmpboilerplate.presentation.movie.category.MovieCategoryScreen
import org.fahimdev.cmpboilerplate.presentation.movie.category.MovieCategoryViewModel
import org.fahimdev.cmpboilerplate.presentation.movie.details.MovieDetailsScreen
import org.fahimdev.cmpboilerplate.presentation.movie.details.MovieDetailsViewModel
import org.fahimdev.cmpboilerplate.presentation.movie.list.MovieListViewModel
import org.fahimdev.cmpboilerplate.presentation.movie.list.toName
import org.fahimdev.cmpboilerplate.presentation.settings.SettingsScreen
import org.fahimdev.cmpboilerplate.presentation.settings.SettingsViewModel
import org.fahimdev.cmpboilerplate.presentation.settings.components.AppearanceTheme
import org.fahimdev.cmpboilerplate.presentation.settings.components.Languages
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    onLanguageSelected: (Languages) -> Unit,
    onAppearanceSelected: (AppearanceTheme) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MovieList
    ) {
        composable<Screen.Settings> {
            val viewModel: SettingsViewModel = koinViewModel()
            SettingsScreen(
                navController = navController,
                onLanguageSelected = {},
                onAppearanceSelected = {
                    onAppearanceSelected(it)
                },
                viewModel = viewModel
            )
        }
        composable<Screen.MovieList> {
            val viewModel: MovieListViewModel = koinViewModel()
            MovieListScreen(
                navController = navController,
                onViewAllClick = { category ->
                    navController.navigate(Screen.MovieCategory(category.toName()))
                },
                onMovieClick = { movieId ->
                    navController.navigate(Screen.MovieDetails(movieId))
                },
                viewModel = viewModel
            )
        }

        composable<Screen.MovieDetails> { backStackEntry ->
            val viewModel: MovieDetailsViewModel = koinViewModel()
            val route = backStackEntry.toRoute<Screen.MovieDetails>()
            MovieDetailsScreen(
                id = route.movieId,
                onBackArrowClick = {
                    navController.navigateUp()
                },
                viewModel = viewModel
            )
        }

        composable<Screen.MovieCategory> { backStackEntry ->
            val viewModel: MovieCategoryViewModel = koinViewModel()
            val category = backStackEntry.toRoute<Screen.MovieCategory>().category
            MovieCategoryScreen(
                type = category,
                onNavigateBack = {
                    navController.navigateUp()
                },
                viewModel = viewModel
            )
        }
    }
}