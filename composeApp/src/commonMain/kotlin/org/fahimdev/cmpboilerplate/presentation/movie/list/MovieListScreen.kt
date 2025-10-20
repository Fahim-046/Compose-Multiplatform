import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import cmpboilerplate.composeapp.generated.resources.Res
import cmpboilerplate.composeapp.generated.resources.app_description
import cmpboilerplate.composeapp.generated.resources.app_name
import cmpboilerplate.composeapp.generated.resources.popular_title
import cmpboilerplate.composeapp.generated.resources.trending_now_title
import cmpboilerplate.composeapp.generated.resources.upcoming_title
import cmpboilerplate.composeapp.generated.resources.view_all_button_label
import org.fahimdev.cmpboilerplate.presentation.movie.list.components.HorizontalCarousel
import org.fahimdev.cmpboilerplate.presentation.movie.list.components.MovieGrid
import org.fahimdev.cmpboilerplate.presentation.movie.list.components.MovieHeader
import org.fahimdev.cmpboilerplate.core.components.shimmer.MovieHeaderShimmer
import org.fahimdev.cmpboilerplate.core.components.shimmer.MovieTileShimmer
import org.fahimdev.cmpboilerplate.core.components.shimmer.ShimmerEffect
import org.fahimdev.cmpboilerplate.core.components.topbar.PrimaryTopBar
import org.fahimdev.cmpboilerplate.domain.model.Movie
import org.fahimdev.cmpboilerplate.presentation.base.BaseScreen
import org.fahimdev.cmpboilerplate.presentation.movie.list.CategoryType
import org.fahimdev.cmpboilerplate.presentation.movie.list.MovieListViewModel
import org.fahimdev.cmpboilerplate.presentation.movie.list.toName
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MovieListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    onViewAllClick: (CategoryType) -> Unit,
    onMovieClick: (Int) -> Unit,
    viewModel: MovieListViewModel = koinViewModel()
) {
    val states = viewModel.states.collectAsStateWithLifecycle()
    val appDescription = stringResource(Res.string.app_description)
    val appName = stringResource(Res.string.app_name)
    MovieListSkeleton(
        appDescription = appDescription,
        appName = appName,
        trendingMovies = states.value.trendingMovies,
        popularMovies = states.value.popularMovies,
        upcomingMovies = states.value.upcomingMovies,
        isLoading = states.value.isLoading,
        navController = navController,
        onViewAllClick = {
            onViewAllClick(it)
        },
        onMovieClick = {
            onMovieClick(it)
        }
    )
}

@Composable
fun MovieListSkeleton(
    modifier: Modifier = Modifier,
    appDescription: String,
    appName: String,
    trendingMovies: List<Movie?>,
    popularMovies: List<Movie?>,
    upcomingMovies: List<Movie?>,
    isLoading: Boolean,
    navController: NavController,
    onViewAllClick: (CategoryType) -> Unit,
    onMovieClick: (Int) -> Unit
) {
    BaseScreen(
        title = appName,
        showTopBar = true,
        showBackArrow = false,
        showBottomNavigation = true,
        navController = navController,
        topBar = {
            PrimaryTopBar(
                title = appName,
            )
        },
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item(key = "carousel") {
                Column {
                    if (isLoading || trendingMovies.isEmpty()) {
                        ShimmerEffect(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(350.dp)
                        )
                    } else {
                        HorizontalCarousel(movies = trendingMovies)
                    }
                }
            }

            if (isLoading || trendingMovies.isEmpty()) {
                item {
                    MovieHeaderShimmer()
                }

                item {
                    MovieTileShimmer()
                }
            }

            if (trendingMovies.isNotEmpty()) {
                item {
                    MovieHeader(header = stringResource(Res.string.trending_now_title), actionText = stringResource(Res.string.view_all_button_label)) {
                        onViewAllClick(CategoryType.Trending)
                    }
                }

                item(key = "trending_grid") {
                    MovieGrid(movies = trendingMovies, onMovieClick = {
                        onMovieClick(it)
                    })
                }
            }

            if (isLoading || popularMovies.isEmpty()) {
                item {
                    MovieHeaderShimmer()
                }

                item {
                    MovieTileShimmer()
                }
            }

            if (popularMovies.isNotEmpty()) {
                item {
                    MovieHeader(header = stringResource(Res.string.popular_title), actionText = stringResource(Res.string.view_all_button_label)) {
                        onViewAllClick(CategoryType.Popular)
                    }
                }

                item(key = "popular_grid") {
                    MovieGrid(movies = popularMovies, onMovieClick = {
                        onMovieClick(it)
                    })
                }
            }

            if (isLoading || upcomingMovies.isEmpty()) {
                item {
                    MovieHeaderShimmer()
                }

                item {
                    MovieTileShimmer()
                }
            }

            if (upcomingMovies.isNotEmpty()) {
                item {
                    MovieHeader(header = stringResource(Res.string.upcoming_title), actionText = stringResource(Res.string.view_all_button_label)) {
                        onViewAllClick(CategoryType.Upcoming)
                    }
                }

                item(key = "upcoming_grid") {
                    MovieGrid(movies = upcomingMovies, onMovieClick = {
                        onMovieClick(it)
                    })
                }
            }
        }
    }
}