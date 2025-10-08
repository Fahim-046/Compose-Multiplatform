package org.fahimdev.cmpboilerplate.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.fahimdev.cmpboilerplate.presentation.movie.list.MovieListScreen
import org.fahimdev.cmpboilerplate.presentation.movie.list.MovieListViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screen.MovieList.route
    ){
        composable(Screen.MovieList.route){
            val viewModel: MovieListViewModel = koinViewModel()
            MovieListScreen(
                onViewAllClick = {},
                onMovieClick = {},
                viewModel = viewModel
            )
        }
    }
}