package org.fahimdev.cmpboilerplate.core.enums

import androidx.compose.runtime.Composable
import cmpboilerplate.composeapp.generated.resources.Res
import cmpboilerplate.composeapp.generated.resources.popular_title
import cmpboilerplate.composeapp.generated.resources.top_rated_title
import cmpboilerplate.composeapp.generated.resources.trending_now_title
import cmpboilerplate.composeapp.generated.resources.upcoming_title
import org.jetbrains.compose.resources.stringResource

enum class MovieCategoryType {
    POPULAR,
    TRENDING,
    TOP_RATED,
    UPCOMING,
    NOW_PLAYING
}

fun MovieCategoryType.toTitle(): String {
    return when (this) {
        MovieCategoryType.POPULAR -> "Popular"
        MovieCategoryType.TRENDING -> "Trending"
        MovieCategoryType.TOP_RATED -> "Top Rated"
        MovieCategoryType.UPCOMING -> "Upcoming"
        MovieCategoryType.NOW_PLAYING -> "Now Playing"
    }
}

@Composable
fun String.toLocalizedTitle(): String {
    return when (this) {
        "Popular" -> stringResource(Res.string.popular_title)
        "Trending" -> stringResource(Res.string.trending_now_title)
        "Top Rated" -> stringResource(Res.string.top_rated_title)
        "Upcoming" -> stringResource(Res.string.upcoming_title)
        "Now Playing" -> "Now Playing"
        else -> ""
    }
}