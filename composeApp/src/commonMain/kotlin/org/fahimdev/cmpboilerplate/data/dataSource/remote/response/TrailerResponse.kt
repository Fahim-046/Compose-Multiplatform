package org.fahimdev.cmpboilerplate.data.dataSource.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class TrailerResponse(
    val iso_639_1: String,
    val iso_3166_1: String,
    val name: String,
    val key: String,
    val site: String,
    val size: Int,
    val type: String,
    val official: Boolean,
    val published_at: String,
    val id: String
)
