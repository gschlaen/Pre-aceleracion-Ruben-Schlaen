package com.example.alkemychallenge.views.movies.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alkemychallenge.data.movies.popular.MovieRemoteDataSource
import com.example.alkemychallenge.data.movies.popular.MovieRepository

class MoviesViewModelFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val remoteDataSource = MovieRemoteDataSource()
        val repository = MovieRepository(remoteDataSource)

        return MoviesViewModel(repository) as T
    }

}