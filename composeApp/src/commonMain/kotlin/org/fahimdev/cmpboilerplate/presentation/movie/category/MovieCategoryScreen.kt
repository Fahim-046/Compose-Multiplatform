package org.fahimdev.cmpboilerplate.presentation.movie.category

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.fahimdev.cmpboilerplate.presentation.movie.list.CategoryType
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MovieCategoryScreen(
    modifier: Modifier = Modifier,
    type: CategoryType,
    onNavigateBack: () -> Unit,
    viewModel: MovieCategoryViewModel = koinViewModel()
) {
    //val moviesPagingItems = viewModel.getMoviesPaging(type).collectAsLazyPagingItems()

    MovieCategorySkeleton(
        modifier = modifier,
        type = type.toString(),
        onNavigateBack = onNavigateBack
    )
}

@Composable
fun MovieCategorySkeleton(
    modifier: Modifier = Modifier,
    type: String,
    onNavigateBack: () -> Unit
) {
//    BaseScreen(title = type, showTopBar = true, showBackArrow = true, topBar = {
//        PrimaryTopBar(
//            title = type,
//            leadingIcon = Icons.AutoMirrored.Filled.ArrowBack,
//            onLeadingIconClick = onNavigateBack,
//        )
//    }) {
//        Column(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            when (movies.loadState.refresh) {
//                is LoadState.Loading -> {
//                    MovieTileShimmer(size = 6)
//                }
//
//                is LoadState.Error -> {
//                    Box(
//                        modifier = Modifier.fillMaxSize(),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        androidx.compose.material3.Text("Error loading movies")
//                    }
//                }
//
//                else -> {
//                    LazyVerticalGrid(
//                        columns = GridCells.Fixed(2),
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(10.dp),
//                        verticalArrangement = Arrangement.spacedBy(16.dp),
//                        horizontalArrangement = Arrangement.spacedBy(16.dp)
//                    ) {
//                        items(movies.itemCount) { index ->
//                            val movie = movies[index]
//                            movie?.let {
//                                MovieTile(movie = it)
//                            }
//                        }
//
//                        if (movies.loadState.append is LoadState.Loading) {
//                            item(span = { androidx.compose.foundation.lazy.grid.GridItemSpan(2) }) {
//                                Box(
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .padding(16.dp),
//                                    contentAlignment = Alignment.Center
//                                ) {
//                                    CircularProgressIndicator(
//                                        modifier = Modifier.size(32.dp),
//                                        color = MaterialTheme.colorScheme.primary
//                                    )
//                                }
//                            }
//                        }
//
//                        if (movies.loadState.append is LoadState.Error) {
//                            item(span = { androidx.compose.foundation.lazy.grid.GridItemSpan(2) }) {
//                                Box(
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .padding(16.dp),
//                                    contentAlignment = Alignment.Center
//                                ) {
//                                    androidx.compose.material3.Text(
//                                        text = "Error loading more movies",
//                                        color = MaterialTheme.colorScheme.error
//                                    )
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
}
