package com.example.databinding

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/now_playing")
    suspend fun getTopRatedMovies(@Query("page") page: Int, @Query("api_key") apiKey: String): Response<MoviesResponse>

    companion object {
        operator fun invoke(): MoviesApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()
                .create(MoviesApi::class.java)
        }
    }
}