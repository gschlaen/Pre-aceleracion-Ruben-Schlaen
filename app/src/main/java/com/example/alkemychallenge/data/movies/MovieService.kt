package com.example.alkemychallenge.data.movies

import com.example.alkemychallenge.data.movies.details.MovieDetails
import com.example.alkemychallenge.data.movies.popular.PopularMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    fun getPopular(@Query("api_key") apiKey: String): Call<PopularMovies>

    @GET("movie/{id}")
    fun getDetails(@Path("id") id: Int,  @Query("api_key") apiKey: String): Call<MovieDetails>
}