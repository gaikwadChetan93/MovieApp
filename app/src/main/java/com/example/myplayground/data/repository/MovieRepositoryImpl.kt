package com.example.myplayground.data.repository

import com.example.myplayground.model.Movie
import com.example.myplayground.domain.repository.MovieRepository

class MovieRepositoryImpl: MovieRepository {
    override suspend fun getMovies(apiKey: String): List<Movie> {
        TODO("Not yet implemented")
    }
}