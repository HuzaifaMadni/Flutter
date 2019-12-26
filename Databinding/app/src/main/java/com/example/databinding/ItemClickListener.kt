package com.example.databinding

import android.view.View
import com.example.databinding.models.movie.Movies

interface ItemClickListener {

    fun movieClicked(view: View, movies: Movies)
}