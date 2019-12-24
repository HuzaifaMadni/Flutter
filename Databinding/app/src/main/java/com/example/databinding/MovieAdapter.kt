package com.example.databinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.databinding.databinding.MovieRowBindingImpl

class MovieAdapter (
    private val movies: List<Movies>
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
    }


    inner class MoviesViewHolder(
        val movieRowBindingImpl: MovieRowBindingImpl
    ) : RecyclerView.ViewHolder(movieRowBindingImpl.root)
}