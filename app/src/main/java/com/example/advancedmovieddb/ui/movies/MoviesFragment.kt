package com.example.advancedmovieddb.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.advancedmovieddb.databinding.FragmentMoviesBinding
import com.example.advancedmovieddb.ui.movies.adapter.MoviesAdapter
import com.example.advancedmovieddb.ui.movies.viewModel.MoviesViewModel
import com.example.advancedmovieddb.ui.movies.viewModel.MoviesViewModelFactory
import kotlinx.coroutines.flow.collectLatest


class MoviesFragment : Fragment() {

    val viewModel : MoviesViewModel by activityViewModels{
        MoviesViewModelFactory()
    }

    private lateinit var binding : FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.photosGrid.adapter = MoviesAdapter()



        return binding.root

    }
}