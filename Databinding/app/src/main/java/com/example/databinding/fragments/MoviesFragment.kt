package com.example.databinding.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databinding.ItemClickListener
import com.example.databinding.adapter.MovieAdapter
import com.example.databinding.R
import com.example.databinding.activities.MovieDetailActivity
import com.example.databinding.models.movie.Movies
import com.example.databinding.networking.MoviesApi
import com.example.databinding.networking.MoviesRepository
import com.example.databinding.viewmodel.MoviesViewModel
import com.example.databinding.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.movies_fragment.*

class MoviesFragment : Fragment(), ItemClickListener {

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

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener {
            activity?.finish()
        }

        val api = MoviesApi()
        val repository = MoviesRepository(api = api)
        factory = ViewModelFactory(repository = repository)
        viewModel = ViewModelProviders.of(this, factory).get(MoviesViewModel::class.java)
        viewModel.getMovies()

        viewModel.movies.observe(viewLifecycleOwner, Observer { movies ->

            recyclerview_movies.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = MovieAdapter(movies, this)
            }
        })
    }

    override fun movieClicked(view: View, movies: Movies) {
        val intent = Intent(requireContext(), MovieDetailActivity::class.java)
        Toast.makeText(requireContext(), movies.id.toString(), Toast.LENGTH_LONG).show()
        activity?.startActivity(intent)
    }
}
