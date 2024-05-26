package com.example.myplayground.top_rated

import androidx.lifecycle.*
import com.example.myplayground.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    fun getMovies(): LiveData<List<Movie>> {
        return movieRepository.getMovies()
    }

    fun fetchMovies() {
        viewModelScope.launch(IO) {
            movieRepository.fetchMovies()
        }
    }
}