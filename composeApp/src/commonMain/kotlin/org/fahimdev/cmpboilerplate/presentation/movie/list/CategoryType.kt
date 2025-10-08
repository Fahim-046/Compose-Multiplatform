package org.fahimdev.cmpboilerplate.presentation.movie.list

enum class CategoryType{
    Trending,
    Popular,
    Upcoming
}

fun CategoryType.toName(): String {
    return when(this){
        CategoryType.Trending -> "Trending"
        CategoryType.Popular -> "Popular"
        CategoryType.Upcoming -> "Upcoming"
    }
}