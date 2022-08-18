package com.example.alkemychallenge.views.movies.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alkemychallenge.data.movies.details.DetailsRepository
import com.example.alkemychallenge.data.movies.details.MovieDetails
import com.example.alkemychallenge.data.response.RepositoryError
import com.example.alkemychallenge.data.response.RepositoryResponse
import com.example.alkemychallenge.data.response.ResponseListener

class DetailsViewModel(
    private val repository: DetailsRepository
): ViewModel() {

    val details = MutableLiveData<MovieDetails?>()
    val error = MutableLiveData<RepositoryError?>()


    fun load(id: Int) {

        repository.getDetails(id = id, object: ResponseListener<MovieDetails> {

            override fun onResponse(response: RepositoryResponse<MovieDetails>) {
                details.value = response.data
            }

            override fun onError(repositoryError: RepositoryError) {
                error.value = repositoryError
            }

        })

    }

}