package com.example.advancedmovieddb.ui.movies.adapter

import android.util.Log
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.advancedmovieddb.R
import com.example.advancedmovieddb.ui.DetailsMoviesUiState


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    Log.e("img","$imgUrl")
    imgUrl.let {
        val imgUri = imgUrl?.toUri()?.buildUpon()?.build()
        Glide.with(imgView)
            .load("https://image.tmdb.org/t/p/w500${imgUri}")
            .centerCrop() // scale image to fill the entire ImageView
            .into(imgView)

    }
}


@BindingAdapter("listData")
fun bindMovies(
    recyclerView: RecyclerView,
    data : List<DetailsMoviesUiState>
){
  val adapter = recyclerView.adapter as MoviesAdapter
  adapter.submitList(data)
}