package com.example.advancedmovieddb.ui.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.advancedmovieddb.data.api.model.Movies
import com.example.advancedmovieddb.data.api.model.MoviesPhoto
import com.example.advancedmovieddb.databinding.MoviesItemBinding
import com.example.advancedmovieddb.ui.DetailsMoviesUiState

class MoviesAdapter : PagingDataAdapter<MoviesPhoto ,
        MoviesAdapter.MoviesViewHolder>(DiffCallback) {

    class MoviesViewHolder(
        private var binding :
                MoviesItemBinding
    ) :
    RecyclerView.ViewHolder(binding.root)
    {
        fun bind(movies : MoviesPhoto){
            binding.result = movies
            binding.executePendingBindings()
        }
        var poster : ImageView = binding.poster
        var title : TextView = binding.title

    }

    companion object DiffCallback : DiffUtil.ItemCallback<MoviesPhoto>() {
        override fun areItemsTheSame(
            oldDetails: MoviesPhoto,
            newDetails: MoviesPhoto
        ): Boolean {
            return newDetails.title == oldDetails.title
        }

        override fun areContentsTheSame(
            oldDetails: MoviesPhoto,
            newDetails: MoviesPhoto
        ): Boolean {
            return oldDetails.title == newDetails.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            MoviesItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
         getItem(position)?.let {
            holder.bind(it)
        }
    }

}