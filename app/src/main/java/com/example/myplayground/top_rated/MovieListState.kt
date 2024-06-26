package com.example.myplayground.top_rated

import com.example.myplayground.data.remote.dto.Movie

data class MovieListState(
    val isLoading: Boolean = false,
    val movieList: List<Movie> = emptyList(),
    val error: String = ""
)