package com.example.myplayground.domain.use_case.get_movies

import com.example.myplayground.common.Resource
import com.example.myplayground.domain.repository.MovieRepository
import com.example.myplayground.data.remote.dto.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val movies = repository.getMovies("3b502b3f-b1ff-4128-bd99-626e74836d9c")
            emit(Resource.Success(movies))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}