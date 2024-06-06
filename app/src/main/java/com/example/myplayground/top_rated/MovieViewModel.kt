package com.example.myplayground.top_rated

import androidx.lifecycle.*
import com.example.myplayground.common.Resource
import com.example.myplayground.data.remote.dto.MovieDetail
import com.example.myplayground.domain.use_case.get_movie.GetMovieUseCase
import com.example.myplayground.domain.use_case.get_movies.GetMoviesUseCase
import com.example.myplayground.movie_detail.MovieDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getMovieUseCase: GetMovieUseCase
) : ViewModel() {

    private val movieList = MutableStateFlow(MovieListState(isLoading = true))
    private val movieDetail = MutableStateFlow(MovieDetailState(isLoading = true))

    fun getMovieList(): StateFlow<MovieListState> {
        return movieList
    }

    fun getMovieDetail(): StateFlow<MovieDetailState> {
        return movieDetail
    }

    fun getMovies() {
        getMoviesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    movieList.value = MovieListState(movieList = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    movieList.value = MovieListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    movieList.value = MovieListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getMovieDetail(id: Int) {
        getMovieUseCase(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    movieDetail.value =
                        MovieDetailState(movieDetail = result.data ?: MovieDetail(name = ""))
                }

                is Resource.Error -> {
                    movieDetail.value = MovieDetailState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    movieDetail.value = MovieDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}