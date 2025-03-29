package com.neopixel.moviesearch.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.neopixel.moviesearch.data.api.ApiModule

class HomeViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(ApiModule.tmdbApi) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
} 