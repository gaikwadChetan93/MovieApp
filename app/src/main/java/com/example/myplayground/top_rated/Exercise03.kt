package com.example.myplayground.top_rated

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myplayground.databinding.ActivityMainBinding
import com.example.myplayground.model.Movie
import com.example.myplayground.network.RetrofitClient
import com.example.topratedmoviewitharchitecturepattern.presentation.top_rated.MovieAdapter

class Exercise03 : AppCompatActivity() {

    private lateinit var movieAdapter: MovieAdapter
    private val movies = arrayListOf<Movie>()
    lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initList()
        movieViewModel =
            ViewModelProvider(
                this,
                MovieViewModel.Factory(
                    MovieRepository(
                        RetrofitClient.getRetrofitClient()
                    )
                )
            )[MovieViewModel::class.java]
        movieViewModel.getMovies().observe(this, Observer {
            movies.addAll(it)
            movieAdapter.notifyDataSetChanged()
        })
        movieViewModel.fetchMovies()
    }

    private fun initList() {
        binding.movieList.layoutManager = LinearLayoutManager(this)
        movieAdapter = MovieAdapter(this, movies)
        binding.movieList.adapter = movieAdapter
    }
}
