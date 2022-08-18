package com.example.alkemychallenge.data.movies.popular

import com.example.alkemychallenge.data.movies.Movie
import com.google.gson.annotations.SerializedName

class PopularMovies(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val movies: List<Movie>
)