package com.example.databinding.utils

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.databinding.R

@BindingAdapter("image")
fun loadImage(view: ImageView, url: String){

    Glide.with(view.context)
        .load(Constants.IMG_URL + url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(view)
}

@SuppressLint("SetTextI18n")
@BindingAdapter("ratings")
fun ratings(view: TextView, rating: Double){

    view.text = "Ratings: $rating"
}