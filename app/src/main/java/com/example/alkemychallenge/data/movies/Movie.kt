package com.example.alkemychallenge.data.movies

import com.example.alkemychallenge.BuildConfig
import com.google.gson.annotations.SerializedName

class Movie (
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("poster_path")
    val poster: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
) {
    fun getPosterUri(): String {
        return "${BuildConfig.IMAGE_URL}$poster"
    }
}