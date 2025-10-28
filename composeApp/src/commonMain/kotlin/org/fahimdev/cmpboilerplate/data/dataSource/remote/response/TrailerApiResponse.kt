package org.fahimdev.cmpboilerplate.data.dataSource.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class TrailerApiResponse(
    val id: Int,
    val results: List<TrailerResponse>
)