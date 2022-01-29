package com.example.advancedmovieddb.ui.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.advancedmovieddb.databinding.LoadingViewBinding
import com.example.advancedmovieddb.databinding.MoviesItemBinding

class LoadingStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoadStateViewHolder>() {
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) =
        holder.bindViewHolder(loadState)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadStateViewHolder = LoadStateViewHolder.createViewHolder(parent, retry)

}


class LoadStateViewHolder(
    private val binding: LoadingViewBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.retry.setOnClickListener { retry.invoke() }
    }

    fun bindViewHolder(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.errorMsg.isVisible = loadState is LoadState.Error
        binding.retry.isVisible = loadState is LoadState.Error
        binding.progress.isVisible = loadState is LoadState.Loading
    }

    companion object {
        fun createViewHolder(parent: ViewGroup, retry: () -> Unit): LoadStateViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = LoadingViewBinding.inflate(layoutInflater, parent, false)
            return LoadStateViewHolder(binding, retry)
        }
    }
}