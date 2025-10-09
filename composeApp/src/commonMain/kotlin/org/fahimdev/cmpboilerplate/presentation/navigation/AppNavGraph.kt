package org.fahimdev.cmpboilerplate.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.fahimdev.cmpboilerplate.presentation.movie.category.MovieCategoryScreen
import org.fahimdev.cmpboilerplate.presentation.movie.category.MovieCategoryViewModel
import org.fahimdev.cmpboilerplate.presentation.movie.details.MovieDetailsScreen
import org.fahimdev.cmpboilerplate.presentation.movie.details.MovieDetailsViewModel
import org.fahimdev.cmpboilerplate.presentation.movie.list.MovieListScreen
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
        startDestination = Screen.Settings.route
    ) {
        composable(Screen.Settings.route) {
            val viewModel: SettingsViewModel = koinViewModel()
            SettingsScreen(
                onLanguageSelected = {
                    onLanguageSelected(it)
                },
                onAppearanceSelected = {
                    onAppearanceSelected(it)
                },
                viewModel = viewModel
            )
        }
        composable(Screen.MovieList.route) {
            val viewModel: MovieListViewModel = koinViewModel()
            MovieListScreen(
                onViewAllClick = { category ->
                    navController.navigate(Screen.MovieCategory.createRoute(category.toName()))
                },
                onMovieClick = { movieId ->
                    navController.navigate(Screen.MovieDetails.createRoute(movieId))
                },
                viewModel = viewModel
            )
        }

        composable(Screen.MovieDetails.route) { backStackEntry ->
            val viewModel: MovieDetailsViewModel = koinViewModel()
            val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull()
            MovieDetailsScreen(
                id = movieId ?: 0,
                onBackArrowClick = {
                    navController.popBackStack()
                },
                viewModel = viewModel
            )
        }

        composable(Screen.MovieCategory.route) { backStackEntry ->
            val viewModel: MovieCategoryViewModel = koinViewModel()
            val category = backStackEntry.arguments?.getString("category")
            MovieCategoryScreen(
                type = category ?: "",
                onNavigateBack = {
                    navController.popBackStack()
                },
                viewModel = viewModel
            )
        }
    }
}