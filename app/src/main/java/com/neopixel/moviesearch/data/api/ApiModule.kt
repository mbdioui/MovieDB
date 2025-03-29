package com.neopixel.moviesearch.data.api

import com.neopixel.moviesearch.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiModule {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(NetworkInterceptor.createLoggingInterceptor())
        .addInterceptor(NetworkInterceptor.createErrorInterceptor())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val tmdbApi: TMDbApi = retrofit.create(TMDbApi::class.java)
} 