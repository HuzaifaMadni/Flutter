package com.example.databinding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.databinding.ItemClickListener
import com.example.databinding.R
import com.example.databinding.databinding.MovieRowBindingImpl
import com.example.databinding.models.movie.Movies
import kotlinx.android.synthetic.main.movie_row.view.*

class MovieAdapter (
    private val movies: List<Movies>,
    private val listener: ItemClickListener
) : RecyclerView.Adapter<MovieAdapter.MoviesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.movie_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.movieRowBindingImpl.movie = movies[position]
        holder.movieRowBindingImpl.root.moviesCard.setOnClickListener {
            listener.movieClicked(holder.movieRowBindingImpl.root.moviesCard, movies[position])
        }
    }

    inner class MoviesViewHolder(
        val movieRowBindingImpl: MovieRowBindingImpl
    ) : RecyclerView.ViewHolder(movieRowBindingImpl.root)
}