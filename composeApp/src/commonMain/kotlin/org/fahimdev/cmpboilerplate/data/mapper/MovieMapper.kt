package org.fahimdev.cmpboilerplate.data.mapper

import org.fahimdev.cmpboilerplate.data.dataSource.remote.response.MovieApiResponse
import org.fahimdev.cmpboilerplate.domain.model.Movie

class MovieMapper{
    companion object{
        fun mapDataToDomain(response: MovieApiResponse): Movie?{
            return Movie(
                id = response.id,
            )
        }
    }
}