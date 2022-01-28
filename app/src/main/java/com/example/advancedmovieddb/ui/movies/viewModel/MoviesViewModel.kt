package com.example.advancedmovieddb.ui.movies.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.advancedmovieddb.domain.GetMoviesUseCase
import com.example.advancedmovieddb.model.Movies
import com.example.advancedmovieddb.ui.DetailsMoviesUiState
import com.example.advancedmovieddb.ui.MoviesUiState
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _moviesUi = MutableStateFlow(MoviesUiState())
    val moviesUi: StateFlow<MoviesUiState> = _moviesUi.asStateFlow()

    init {
        getMovies()
    }

     fun getMovies() {
        viewModelScope.launch {
            val movies = getMoviesUseCase.invoke()
            val moviesDeatiles = movies.results.map {
                DetailsMoviesUiState(
                    title = it.title,
                    overview = it.overview,
                    poster = it.posterPath
                )
            }
            _moviesUi.update {
                it.copy(
                    moviesList = moviesDeatiles
                )
            }

        }
    }

}