package com.example.alkemychallenge.views.movies.popular

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alkemychallenge.data.movies.Movie
import com.example.alkemychallenge.data.movies.popular.MovieRepository
import com.example.alkemychallenge.data.movies.popular.PopularMovies
import com.example.alkemychallenge.data.response.RepositoryError
import com.example.alkemychallenge.data.response.RepositoryResponse
import com.example.alkemychallenge.data.response.ResponseListener

class MoviesViewModel(
    private val repository: MovieRepository
): ViewModel() {

    val movies = MutableLiveData<List<Movie>>(null)
    val error = MutableLiveData<RepositoryError?>()

    fun load() {

        repository.getPopular(object: ResponseListener<PopularMovies> {

            override fun onResponse(response: RepositoryResponse<PopularMovies>) {
                movies.value = response.data.movies
            }

            override fun onError(repositoryError: RepositoryError) {
                error.value = repositoryError
            }

        })

    }

}