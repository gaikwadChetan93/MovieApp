package com.example.myplayground.data.repository

import com.example.myplayground.data.remote.MovieApi
import com.example.myplayground.data.remote.dto.Movie
import com.example.myplayground.domain.repository.MovieRepository
import retrofit2.Retrofit
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val retrofit: Retrofit
) : MovieRepository {
    override suspend fun getMovies(apiKey: String): List<Movie> {
        return retrofit
            .create(MovieApi::class.java)
            .fetchTopRatedMovies(apiKey)
            .results
    }
}