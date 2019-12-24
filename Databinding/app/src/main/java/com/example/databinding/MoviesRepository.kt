package com.example.databinding

class MoviesRepository(
    private val api: MoviesApi
): SafeApiRequest() {

    suspend fun getMovies() = apiRequest{ api.getTopRatedMovies(page = 1, apiKey = Constants.API_KEY)}
}