package com.example.alkemychallenge.data.movies.popular

import com.example.alkemychallenge.data.response.ResponseListener

class MovieRepository(
    private val remoteDataSource: MovieRemoteDataSource
) {

    fun getPopular(listener: ResponseListener<PopularMovies>) {
        this.remoteDataSource.getPopular(listener)
    }

}