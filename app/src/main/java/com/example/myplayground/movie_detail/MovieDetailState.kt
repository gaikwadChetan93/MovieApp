package com.example.myplayground.movie_detail

import com.example.myplayground.data.remote.dto.MovieDetail

data class MovieDetailState(
    val isLoading: Boolean = false,
    val movieDetail: MovieDetail = MovieDetail(name = ""),
    val error: String = ""
)