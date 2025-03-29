package com.neopixel.moviesearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.neopixel.moviesearch.ui.components.EmptyState
import com.neopixel.moviesearch.ui.components.MovieCard
import com.neopixel.moviesearch.ui.components.MovieCardShimmer
import com.neopixel.moviesearch.ui.home.HomeViewModel
import com.neopixel.moviesearch.ui.theme.MovieSearchTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieSearchTheme {
                val viewModel: HomeViewModel = hiltViewModel()
                val searchQuery by viewModel.searchQuery.collectAsState()
                val movies by viewModel.movies.collectAsState()
                val isLoading by viewModel.isLoading.collectAsState()
                val error by viewModel.error.collectAsState()

                val snackbarHostState = remember { SnackbarHostState() }

                // Show error snackbar if there's an error
                LaunchedEffect(error) {
                    error?.let {
                        snackbarHostState.showSnackbar(
                            message = it,
                            duration = SnackbarDuration.Short
                        )
                        viewModel.clearError()
                    }
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        SearchBar(
                            query = searchQuery,
                            onQueryChange = { viewModel.updateSearchQuery(it) },
                            onSearch = { viewModel.updateSearchQuery(searchQuery) },
                            active = false,
                            onActiveChange = {},
                            placeholder = { Text("Search movies...") },
                            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {}
                    },
                    snackbarHost = { SnackbarHost(snackbarHostState) }
                ) { paddingValues ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        when {
                            isLoading -> {
                                LazyColumn(
                                    modifier = Modifier.fillMaxSize(),
                                    contentPadding = PaddingValues(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    items(5) {
                                        MovieCardShimmer()
                                    }
                                }
                            }
                            movies.isEmpty() -> {
                                EmptyState()
                            }
                            else -> {
                                LazyColumn(
                                    modifier = Modifier.fillMaxSize(),
                                    contentPadding = PaddingValues(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    items(movies) { movie ->
                                        MovieCard(movie = movie)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}