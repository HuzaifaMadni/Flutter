package com.example.databinding.models.movie

import com.example.databinding.models.movie.Dates
import com.example.databinding.models.movie.Movies

data class MoviesResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Movies>,
    val total_pages: Int,
    val total_results: Int
)