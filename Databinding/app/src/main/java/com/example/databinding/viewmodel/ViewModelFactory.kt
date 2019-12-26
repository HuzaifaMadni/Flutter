package com.example.databinding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.databinding.networking.MoviesRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: MoviesRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.simpleName == MoviesDetailViewModel::class.java.simpleName) {
            return MoviesDetailViewModel(repository) as T
        } else {
            return MoviesViewModel(repository) as T
        }
    }

}