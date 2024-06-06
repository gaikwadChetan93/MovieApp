package com.example.myplayground.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @SerializedName("Duration") var duration: String? = null,
    @SerializedName("Description") var description: String? = null,
    @SerializedName("Director") var director: String? = null,
    @SerializedName("Genres") var genres: ArrayList<String> = arrayListOf(),
    @SerializedName("Actors") var actors: ArrayList<String> = arrayListOf(),
    @SerializedName("Id") var id: Int? = null,
    @SerializedName("Name") var name: String? = null
)