package com.example.databinding.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.databinding.R
import com.example.databinding.databinding.ActivityMovieDetailBinding
import com.example.databinding.networking.MoviesApi
import com.example.databinding.networking.MoviesRepository
import com.example.databinding.viewmodel.MoviesDetailViewModel
import com.example.databinding.viewmodel.MoviesViewModel
import com.example.databinding.viewmodel.ViewModelFactory

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: MoviesDetailViewModel
    private lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val binding: ActivityMovieDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)

        val api = MoviesApi()
        val repository = MoviesRepository(api = api)
        factory = ViewModelFactory(repository = repository)
        viewModel = ViewModelProviders.of(this, factory).get(MoviesDetailViewModel::class.java)
        viewModel.getMovies()

        viewModel.movies.observe(this, Observer { movies ->
            binding.movie = movies
        })
    }
}
