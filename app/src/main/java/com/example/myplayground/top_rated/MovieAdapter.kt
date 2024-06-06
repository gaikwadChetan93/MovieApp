package com.example.myplayground.top_rated

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myplayground.R
import com.example.myplayground.data.remote.dto.Movie
import com.squareup.picasso.Picasso

class MovieAdapter internal constructor(context: Context, private val movies: ArrayList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private val mInflater: LayoutInflater
    private lateinit var mClickListener: MovieClickedListener

    init {
        this.mInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.movie_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.movieTitle.text = movie.name
        Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(holder.movieImg);
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var movieTitle: TextView
        var movieImg: ImageView

        init {
            movieTitle = itemView.findViewById(R.id.tvMovieTitle)
            movieImg = itemView.findViewById(R.id.img)
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            mClickListener.onMovieClick(view, movies[adapterPosition])
        }
    }

    internal fun setClickListener(itemClickListener: MovieClickedListener) {
        this.mClickListener = itemClickListener
    }

    interface MovieClickedListener {
        fun onMovieClick(view: View, movie: Movie)
    }
}