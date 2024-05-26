package com.example.myplayground.domain.repository

import com.example.myplayground.model.Movie

interface MovieRepository {
    suspend fun getMovies(apiKey: String): List<Movie>
}