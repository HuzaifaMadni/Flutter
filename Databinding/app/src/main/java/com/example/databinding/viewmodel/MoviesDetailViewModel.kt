package com.example.databinding.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.databinding.models.movie.Movies
import com.example.databinding.models.movie_detail.MovieDetail
import com.example.databinding.networking.MoviesRepository
import com.example.databinding.utils.Coroutines
import kotlinx.coroutines.Job

class MoviesDetailViewModel (
    val repo: MoviesRepository
) : ViewModel() {

    private lateinit var job: Job

    private val _movies = MutableLiveData<MovieDetail>()
    val movies: LiveData<MovieDetail>
        get() = _movies

    fun getMovies(){
        job = Coroutines.ioThenMain(
            {
                repo.getDetails()
            },
            {
                _movies.value = it
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }
}