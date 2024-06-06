package com.example.myplayground.domain.repository

import com.example.myplayground.data.remote.dto.Movie
import com.example.myplayground.data.remote.dto.MovieDetail

interface MovieRepository {
    suspend fun getMovies(apiKey: String): List<Movie>
    suspend fun getMovie(apiKey: String, movieId: IntArray): MovieDetail
}