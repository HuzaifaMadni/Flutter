package com.example.databinding

data class MoviesResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Movies>,
    val total_pages: Int,
    val total_results: Int
)