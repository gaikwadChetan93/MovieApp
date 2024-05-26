package com.example.myplayground.domain.repository

import com.example.myplayground.data.remote.dto.Movie

interface MovieRepository {
    suspend fun getMovies(apiKey: String): List<Movie>
}