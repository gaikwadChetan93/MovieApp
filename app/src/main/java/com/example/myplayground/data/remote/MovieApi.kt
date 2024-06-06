package com.example.myplayground.data.remote

import com.example.myplayground.data.remote.dto.Movie
import com.example.myplayground.data.remote.dto.MovieDetail
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("/api/1/FEE/MoviesByRank")
    suspend fun fetchTopRatedMovies(
        @Query("authToken") apiKey: String,
        @Query("startRankIndex") startRankIndex: String,
        @Query("numMovies") numMovies: String
    ): List<Movie>

    @GET("/api/1/FEE/MovieDetails")
    suspend fun fetchMovieDetail(
        @Query("authToken") apiKey: String,
        @Query("movieIds") movieIds: IntArray,
    ): List<MovieDetail>
}