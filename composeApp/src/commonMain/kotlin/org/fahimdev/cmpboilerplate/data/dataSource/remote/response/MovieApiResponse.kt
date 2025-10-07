package org.fahimdev.cmpboilerplate.data.dataSource.remote.response

data class MovieApiResponse(
    val page: Int,
    val results: List<MovieResponse>,
    val total_pages: Int,
    val total_results: Int
)
