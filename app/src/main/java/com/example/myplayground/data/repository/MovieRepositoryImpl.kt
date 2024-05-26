package com.example.myplayground.data.repository

import com.example.myplayground.data.remote.MovieApi
import com.example.myplayground.data.remote.dto.Movie
import com.example.myplayground.domain.repository.MovieRepository
import com.example.myplayground.domain.use_case.get_movies.GetMoviesUseCase
import retrofit2.Retrofit
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val retrofit: Retrofit
) : MovieRepository {
    companion object {
        const val START_INDEX = "1"
        const val NUM_MOVIES = "10"
    }

    override suspend fun getMovies(apiKey: String): List<Movie> {
        return retrofit
            .create(MovieApi::class.java)
            .fetchTopRatedMovies(apiKey, START_INDEX, NUM_MOVIES)
    }
}