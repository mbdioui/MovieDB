package com.neopixel.moviesearch.data.api

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

object NetworkInterceptor {
    fun createLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    fun createErrorInterceptor(): Interceptor {
        return object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request()
                val response = chain.proceed(request)
                
                if (!response.isSuccessful) {
                    throw Exception("HTTP ${response.code} ${response.message}")
                }
                
                return response
            }
        }
    }
} 