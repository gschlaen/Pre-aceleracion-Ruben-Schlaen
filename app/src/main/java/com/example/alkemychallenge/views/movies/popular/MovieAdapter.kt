package com.example.alkemychallenge.views.movies.popular

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemychallenge.BuildConfig
import com.example.alkemychallenge.data.movies.Movie
import com.example.alkemychallenge.databinding.ListItemMovieBinding
import com.squareup.picasso.Picasso


class MovieAdapter(var listOfMovie: List<Movie>, val listener: MovieListener? = null): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    interface MovieListener {
        fun select(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemMovieBinding.inflate(layoutInflater, parent,false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listOfMovie[position])
    }

    override fun getItemCount(): Int {
        return listOfMovie.size
    }


    inner class MovieViewHolder(private val binding: ListItemMovieBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            Picasso.get().load(movie.getPosterUri()).into(binding.poster)
            binding.movieTitle.text = movie.title
            binding.movieScore.text = movie.voteAverage.toString()
            binding.movieOverview.text = movie.overview

            binding.root.setOnClickListener {
                listener?.select(movie)
            }
        }
    }

}