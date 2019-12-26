package com.example.databinding.networking

import com.example.databinding.networking.MoviesApi
import com.example.databinding.networking.SafeApiRequest
import com.example.databinding.utils.Constants

class MoviesRepository(
    private val api: MoviesApi
): SafeApiRequest() {

    suspend fun getMovies() = apiRequest{ api.getTopRatedMovies(page = 1, apiKey = Constants.API_KEY)}
    suspend fun getDetails() = apiRequest { api.getMovieDetail(id = Constants.MOV_ID, apiKey = Constants.API_KEY) }
}