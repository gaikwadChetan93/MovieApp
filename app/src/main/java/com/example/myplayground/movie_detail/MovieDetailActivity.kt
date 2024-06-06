package com.example.myplayground.movie_detail

import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.myplayground.databinding.ActivityMovieDetailBinding
import com.example.myplayground.top_rated.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint

class MovieDetailActivity : AppCompatActivity() {

    private val movieViewModel: MovieViewModel by viewModels()
    private lateinit var binding: ActivityMovieDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                movieViewModel.getMovieDetail().collect { uiState ->
                    when {
                        uiState.isLoading -> {
                            Toast.makeText(this@MovieDetailActivity, "Loading", Toast.LENGTH_SHORT)
                                .show()
                        }

                        uiState.movieDetail.name?.isNotEmpty() == true -> {
                            uiState.movieDetail.apply {
                                binding.name.text = name
                                binding.desc.text = description
                                binding.dur.text = duration
                            }
                        }

                        else -> {
                            Toast.makeText(this@MovieDetailActivity, "Error", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }

        movieViewModel.getMovieDetail(intent.extras?.getInt("id") ?: 0)
        binding.book.setOnClickListener {
            openUrl()
        }
    }

    private fun openUrl() {
        val url = "https://www.zocdoc.com/"
        try {
            val i = Intent("android.intent.action.MAIN")
            i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"))
            i.addCategory("android.intent.category.LAUNCHER")
            i.setData(Uri.parse(url))
            startActivity(i)
        } catch (e: ActivityNotFoundException) {
            // Chrome is not installed
            val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(i)
        }
    }
}