package com.example.alkemychallenge.views.movies.details


import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.alkemychallenge.databinding.ActivityDetailsBinding
import com.example.alkemychallenge.views.movies.popular.ID
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

class DetailsActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private val viewModel: DetailsViewModel by viewModels(
        factoryProducer = { DetailsViewModelFactory() }
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityDetailsBinding.inflate(this.layoutInflater)
        setContentView(this.binding.root)

        val actionBar = this.supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = ""

        viewModel.details.observe(this){ details ->
            if(details != null) {
                actionBar?.title = details.title
                binding.progressBar.visibility = View.INVISIBLE

                Picasso.get().load(details.getBackdropUri()).into(binding.backdrop)
                Picasso.get().load(details.getPosterUri()).into(binding.poster)
                binding.title.text = details.title
                val genres = details.genres.map {it["name"]}
                binding.genre.text = genres.joinToString(", ")
                val formatter = SimpleDateFormat("dd/MM/yyyy")
                binding.date.text = formatter.format(details.date)
                binding.language.text =  "- Original language: " + details.getOriginalLanguage()
                binding.popularity.text = "Popularity: " + details.popularity.toString()
                binding.overview.text = details.overview

                binding.detailsContent.visibility = View.VISIBLE
            }
        }

        viewModel.error.observe(this){ error ->
            if(error != null) {
                binding.detailsContent.visibility = View.INVISIBLE
                binding.progressBar.visibility = View.INVISIBLE
                binding.errorText.text = error.message
                binding.error.visibility = View.VISIBLE
            }
        }

        val id = this.intent.extras?.getInt(ID)
        if (id != null) {
            binding.detailsContent.visibility = View.INVISIBLE
            binding.error.visibility = View.INVISIBLE
            viewModel.load(id = id)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}