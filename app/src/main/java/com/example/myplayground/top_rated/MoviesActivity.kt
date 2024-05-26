package com.example.myplayground.top_rated

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myplayground.databinding.ActivityMainBinding
import com.example.myplayground.data.remote.dto.Movie
import com.example.topratedmoviewitharchitecturepattern.presentation.top_rated.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {

    private lateinit var movieAdapter: MovieAdapter
    private val movies = arrayListOf<Movie>()
    private val movieViewModel: MovieViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initList()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                movieViewModel.getUsers().collect { uiState ->
                    when {
                        uiState.isLoading -> {
                            Toast.makeText(this@MoviesActivity, "Loading", Toast.LENGTH_SHORT)
                                .show()
                        }

                        uiState.coins.isNotEmpty() -> {
                            movies.addAll(uiState.coins)
                            movieAdapter.notifyDataSetChanged()
                        }

                        else -> {
                            Toast.makeText(this@MoviesActivity, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun initList() {
        binding.movieList.layoutManager = LinearLayoutManager(this)
        movieAdapter = MovieAdapter(this, movies)
        binding.movieList.adapter = movieAdapter
    }
}
