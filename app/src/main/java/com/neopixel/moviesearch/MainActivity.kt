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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.neopixel.moviesearch.data.ResultWrapper
import com.neopixel.moviesearch.data.model.MovieDTO
import com.neopixel.moviesearch.ui.components.EmptyState
import com.neopixel.moviesearch.ui.components.MovieCard
import com.neopixel.moviesearch.ui.components.MovieCardShimmer
import com.neopixel.moviesearch.ui.theme.MovieSearchTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieSearchTheme {
                var searchQuery by remember { mutableStateOf("") }
                var movies by remember { mutableStateOf<List<MovieDTO>>(emptyList()) }
                var isLoading by remember { mutableStateOf(false) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        SearchBar(
                            query = searchQuery,
                            onQueryChange = { searchQuery = it },
                            onSearch = { /* TODO: Implement search */ },
                            active = false,
                            onActiveChange = {},
                            placeholder = { Text("Search movies...") },
                            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {}
                    }
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