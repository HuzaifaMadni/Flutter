package com.example.databinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

class MoviesViewModel(
    private val repository: MoviesRepository
) : ViewModel() {

    private lateinit var job: Job

    private val _movies = MutableLiveData<List<Movies>>()
    val movies: LiveData<List<Movies>>
        get() = _movies

    fun getMovies(){
        job = Coroutines.ioThenMain(
            { repository.getMovies() },
            { _movies.value = it?.results }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }
}
