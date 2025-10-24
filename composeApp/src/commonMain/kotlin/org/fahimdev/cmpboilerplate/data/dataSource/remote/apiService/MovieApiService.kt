package org.fahimdev.cmpboilerplate.data.dataSource.remote.apiService

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.fahimdev.cmpboilerplate.data.dataSource.remote.response.MovieApiResponse
import org.fahimdev.cmpboilerplate.data.dataSource.remote.response.MovieResponse
import org.fahimdev.cmpboilerplate.data.dataSource.remote.response.TrailerApiResponse

class MovieApiService(
    private val httpClient: HttpClient
) {
    suspend fun getTrendingMovies(page: Int = 1): MovieApiResponse {
        return httpClient.get("movie/now_playing") {
            url {
                parameters.append("page", "$page")
            }
        }.body()
    }

    suspend fun getUpcomingMovies(page: Int = 1): MovieApiResponse {
        return httpClient.get("movie/upcoming"){
            url {
                parameters.append("page", "$page")
            }
        }.body()
    }

    suspend fun getPopularMovies(page: Int = 1): MovieApiResponse {
        return httpClient.get("movie/popular"){
            url {
                parameters.append("page", "$page")
            }
        }.body()
    }

    suspend fun getMovieDetails(id: Int): MovieResponse {
        return httpClient.get("movie/$id").body()
    }

    suspend fun getMovieTrailer(id: Int): TrailerApiResponse{
        return httpClient.get("movie/$id/videos").body()
    }
}