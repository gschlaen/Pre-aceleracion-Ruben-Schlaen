package com.example.alkemychallenge.views.movies.popular

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alkemychallenge.data.movies.Movie
import com.example.alkemychallenge.databinding.ActivityMoviesBinding
import com.example.alkemychallenge.views.movies.details.DetailsActivity

const val ID = "id"

class MoviesActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding
    private val viewModel: MoviesViewModel by viewModels(
        factoryProducer = { MoviesViewModelFactory() }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMoviesBinding.inflate(this.layoutInflater)
        setContentView(this.binding.root)

        val adapter = MovieAdapter(listOf(), object: MovieAdapter.MovieListener {
            override fun select(movie: Movie) {
                val intent = Intent(this@MoviesActivity, DetailsActivity::class.java)
                intent.putExtra(ID, movie.id)
                startActivity(intent)
            }
        })

        binding.moviesList.layoutManager = LinearLayoutManager(this)
        binding.moviesList.adapter = adapter


        viewModel.movies.observe(this) { listOfMovies ->
            if (listOfMovies != null) {
                println(listOfMovies.size)
                binding.progressBar.visibility = View.INVISIBLE
                adapter.listOfMovie = listOfMovies
                adapter.notifyDataSetChanged()

            }
        }

        viewModel.error.observe(this){ error ->
            if(error != null) {
                binding.progressBar.visibility = View.INVISIBLE
                binding.errorText.text = error.message
                binding.error.visibility = View.VISIBLE
            }
        }

        binding.error.visibility = View.INVISIBLE
        viewModel.load()

    }

}