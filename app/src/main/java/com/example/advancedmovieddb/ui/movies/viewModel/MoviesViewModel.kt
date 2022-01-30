package com.example.advancedmovieddb.ui.movies.viewModel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.advancedmovieddb.data.api.model.MoviesPhoto
import com.example.advancedmovieddb.data.repository.MoviesRepository
import com.example.advancedmovieddb.ui.MoviesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    private val _moviesUi = MutableStateFlow(MoviesUiState())
    val moviesUi: StateFlow<MoviesUiState> = _moviesUi.asStateFlow()


    fun getPopularMovies(): Flow<PagingData<MoviesPhoto>> {
        return repository.getMoviesRepo()
            .cachedIn(viewModelScope).flowOn(Dispatchers.IO)
    }

//    viewModelScope.launch {
//        val movies = repository.getMovies()
//        val moviesDeatiles = movies.results.map {
//            DetailsMoviesUiState(
//                title = it.title!!,
//                overview = it.overview,
//                poster = it.posterPath!!
//            )
//        }
//        _moviesUi.update {
//            it.copy(
//                moviesList = moviesDeatiles
//            )
//        }
//
//    }
}

