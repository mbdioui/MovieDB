package com.neopixel.moviesearch.di

import com.neopixel.moviesearch.data.api.ApiModule
import com.neopixel.moviesearch.data.api.TMDbApi
import com.neopixel.moviesearch.data.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideTMDbApi(): TMDbApi {
        return ApiModule.tmdbApi
    }

    @Provides
    @Singleton
    fun provideMovieRepository(tmdbApi: TMDbApi): MovieRepository {
        return MovieRepository(tmdbApi)
    }
} 