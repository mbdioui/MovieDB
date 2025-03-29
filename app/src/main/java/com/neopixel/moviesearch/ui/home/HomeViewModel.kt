package com.neopixel.moviesearch.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neopixel.moviesearch.data.model.MovieDTO
import com.neopixel.moviesearch.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _movies = MutableStateFlow<List<MovieDTO>>(emptyList())
    val movies: StateFlow<List<MovieDTO>> = _movies.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    // Memory cache for search results
    private val searchCache = mutableMapOf<String, List<MovieDTO>>()

    init {
        // Setup search query debounce
        _searchQuery
            .filter { it.isNotBlank() }
            .debounce(300)
            .onEach { query ->
                searchMovies(query)
            }
            .launchIn(viewModelScope)
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    private fun searchMovies(query: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                // Check cache first
                searchCache[query]?.let { cachedMovies ->
                    _movies.value = cachedMovies
                    _isLoading.value = false
                    return@launch
                }

                // Make API call if not in cache
                movieRepository.searchMovies(query)
                    .collect { movies ->
                        // Update cache and state
                        searchCache[query] = movies
                        _movies.value = movies
                    }
            } catch (e: Exception) {
                _error.value = e.message ?: "An error occurred while searching movies"
                _movies.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearError() {
        _error.value = null
    }
} 