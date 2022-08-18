package com.example.alkemychallenge.data.movies.popular


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

class MovieRemoteDataSource {

    fun getPopular(listener: ResponseListener<PopularMovies>) {
        val service = RetrofitService.instance
            .create(MovieService::class.java)
            .getPopular(BuildConfig.API_KEY)

        service.enqueue(object : Callback<PopularMovies> {

            override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
                val popularMovies = response.body()

                if (response.isSuccessful && popularMovies != null) {
                    listener.onResponse(
                        RepositoryResponse(
                            data = popularMovies,
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

            override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
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