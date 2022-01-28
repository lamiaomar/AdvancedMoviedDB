package com.example.advancedmovieddb.ui.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.advancedmovieddb.databinding.MoviesItemBinding
import com.example.advancedmovieddb.ui.DetailsMoviesUiState

class MoviesAdapter : ListAdapter<DetailsMoviesUiState ,
        MoviesAdapter.MoviesViewHolder>(DiffCallback) {

    class MoviesViewHolder(
        private var binding :
                MoviesItemBinding
    ) :
    RecyclerView.ViewHolder(binding.root)
    {
        fun bind(movies : DetailsMoviesUiState){
            binding.result = movies
            binding.executePendingBindings()
        }
        var poster : ImageView = binding.poster
        var title : TextView = binding.title

    }

    companion object DiffCallback : DiffUtil.ItemCallback<DetailsMoviesUiState>() {
        override fun areItemsTheSame(
            oldDetails: DetailsMoviesUiState,
            newDetails: DetailsMoviesUiState
        ): Boolean {
            return newDetails.title == oldDetails.title
        }

        override fun areContentsTheSame(
            oldDetails: DetailsMoviesUiState,
            newDetails: DetailsMoviesUiState
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
        val poster = getItem(position)
        holder.bind(poster)
    }

}