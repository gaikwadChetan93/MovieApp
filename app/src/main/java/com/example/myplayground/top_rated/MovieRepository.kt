package com.example.myplayground.top_rated

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myplayground.data.remote.MovieApi
import com.example.myplayground.model.Movie
import retrofit2.Retrofit
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val retrofit: Retrofit
) {

    private val movieResponseLiveData = MutableLiveData<List<Movie>>()

    fun getMovies(): LiveData<List<Movie>> {
        return movieResponseLiveData
    }

    suspend fun fetchMovies() {
        Log.d("Movie logs", "Movies fetching from API")
        val movieApi = retrofit.create(MovieApi::class.java)
        try {
            val movieResponse =
                movieApi.fetchTopRatedMovies("1d9a4704d81b27085f142914119d38fe")
            movieResponseLiveData.postValue(movieResponse.results)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}