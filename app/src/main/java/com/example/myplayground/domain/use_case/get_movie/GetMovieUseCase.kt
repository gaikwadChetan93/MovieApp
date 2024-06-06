package com.example.myplayground.domain.use_case.get_movie

import com.example.myplayground.common.Resource
import com.example.myplayground.domain.repository.MovieRepository
import com.example.myplayground.data.remote.dto.MovieDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(movieId: Int): Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading())
            val movieDetail =
                repository.getMovie("3b502b3f-b1ff-4128-bd99-626e74836d9c", intArrayOf(movieId))
            emit(Resource.Success(movieDetail))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}