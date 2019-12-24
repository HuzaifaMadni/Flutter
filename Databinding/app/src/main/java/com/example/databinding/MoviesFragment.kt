package com.example.databinding

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.movies_fragment.*

class MoviesFragment : Fragment() {

    private lateinit var viewModel: MoviesViewModel

    private lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val api = MoviesApi()
        val repository = MoviesRepository(api = api)
        factory = ViewModelFactory(repository = repository)
        viewModel = ViewModelProviders.of(this, factory).get(MoviesViewModel::class.java)
        viewModel.getMovies()
        viewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            recyclerview_movies.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = MovieAdapter(movies)
            }
        })
    }
}
