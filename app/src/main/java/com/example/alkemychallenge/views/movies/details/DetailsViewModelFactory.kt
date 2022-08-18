package com.example.alkemychallenge.views.movies.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alkemychallenge.data.movies.details.DetailsRemoteDataSource
import com.example.alkemychallenge.data.movies.details.DetailsRepository

class DetailsViewModelFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val remoteDataSource = DetailsRemoteDataSource()
        val repository = DetailsRepository(remoteDataSource)

        return DetailsViewModel(repository) as T
    }

}