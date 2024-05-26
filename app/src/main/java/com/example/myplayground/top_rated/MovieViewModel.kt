package com.example.myplayground.top_rated

import androidx.lifecycle.*
import com.example.myplayground.common.Resource
import com.example.myplayground.domain.use_case.get_movies.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val users = MutableStateFlow(MovieListState(isLoading = true))


    init {
        getMovies()
    }

    fun getUsers(): StateFlow<MovieListState> {
        return users
    }
    private fun getMovies() {
        getMoviesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    users.value = MovieListState(coins = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    users.value = MovieListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    users.value = MovieListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}