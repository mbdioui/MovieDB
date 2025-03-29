package com.neopixel.moviesearch.data.api

import com.neopixel.moviesearch.data.model.MovieSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("page") page: Int = 1
    ): MovieSearchResponse
} 