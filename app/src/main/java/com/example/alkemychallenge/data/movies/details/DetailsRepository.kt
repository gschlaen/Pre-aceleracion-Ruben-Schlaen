package com.example.alkemychallenge.data.movies.details

import com.example.alkemychallenge.data.response.ResponseListener

class DetailsRepository(
    private val remoteDataSource: DetailsRemoteDataSource
) {

    fun getDetails(id: Int, listener: ResponseListener<MovieDetails>) {
        this.remoteDataSource.getDetails(id = id, listener)
    }

}