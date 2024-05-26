package com.example.myplayground.data.remote.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    @SerializedName("Rank")
    val rank: Int,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("Name")
    val name: String
): Parcelable

data class MovieListResponse(
    val page: String,
    val results: ArrayList<Movie>
)