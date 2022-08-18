package com.example.alkemychallenge.data.movies.details

import com.example.alkemychallenge.BuildConfig
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

class MovieDetails (
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("backdrop_path")
    val backdrop: String,
    @SerializedName("poster_path")
    val poster: String,
    @SerializedName("genres")
    val genres: List<Map<String, String>>,
    @SerializedName("original_language")
    val language: String,
    @SerializedName("release_date")
    val date: Date,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
){
    fun getPosterUri(): String {
        return "${BuildConfig.IMAGE_URL}$poster"
    }

    fun getBackdropUri(): String {
        return "${BuildConfig.IMAGE_URL}$backdrop"
    }

    fun getOriginalLanguage(): String {
        return Locale(this.language).displayLanguage
    }
}