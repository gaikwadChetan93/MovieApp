package com.example.myplayground.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val vote_average: Double
): Parcelable

data class MovieListResponse(
    val page: String,
    val results: ArrayList<Movie>
)