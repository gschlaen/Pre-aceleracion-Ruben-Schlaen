package com.example.alkemychallenge.data.movies.details

import com.example.alkemychallenge.BuildConfig
import com.example.alkemychallenge.data.RetrofitService
import com.example.alkemychallenge.data.movies.MovieService
import com.example.alkemychallenge.data.response.RepositoryError
import com.example.alkemychallenge.data.response.RepositoryResponse
import com.example.alkemychallenge.data.response.ResponseListener
import com.example.alkemychallenge.data.response.Source
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsRemoteDataSource {

    fun getDetails(id:Int, listener: ResponseListener<MovieDetails>) {
        val service = RetrofitService.instance
            .create(MovieService::class.java)
            .getDetails(id = id, BuildConfig.API_KEY)

        service.enqueue(object : Callback<MovieDetails> {

            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                val movieDetails = response.body()

                if (response.isSuccessful && movieDetails != null) {
                    listener.onResponse(
                        RepositoryResponse(
                            data = movieDetails,
                            source = Source.REMOTE
                        )
                    )
                } else {
                    listener.onError(
                        RepositoryError(
                            message = "El servidor rechazó la solicitud",
                            code = response.code(),
                            source = Source.REMOTE
                        )
                    )
                }
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                listener.onError(
                    RepositoryError(
                        message = t.message ?: "Error inesperado",
                        code = -1,
                        source = Source.REMOTE
                    )
                )
            }

        })

    }

}