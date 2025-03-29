package com.neopixel.moviesearch.data.repository

import com.neopixel.moviesearch.data.api.TMDbApi
import com.neopixel.moviesearch.data.model.MovieDTO
import com.neopixel.moviesearch.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val tmdbApi: TMDbApi
) {
    fun searchMovies(query: String): Flow<List<MovieDTO>> = flow {
        try {
            val response = tmdbApi.searchMovies(
                authorization = "Bearer ${Constants.TMDB_TOKEN}",
                query = query,
                page = 1
            )
            emit(response.results)
        } catch (e: Exception) {
            emit(emptyList())
            throw e
        }
    }

    suspend fun getMovieDetails(movieId: Int) = try {
        tmdbApi.getMovieDetails(
            authorization = "Bearer ${Constants.TMDB_TOKEN}",
            movieId = movieId
        )
    } catch (e: Exception) {
        throw e
    }
} 