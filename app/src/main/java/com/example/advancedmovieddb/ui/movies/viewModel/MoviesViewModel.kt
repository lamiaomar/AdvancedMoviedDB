package com.example.advancedmovieddb.ui.movies.viewModel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.advancedmovieddb.domain.GetMoviesUseCase
import com.example.advancedmovieddb.data.api.model.MoviesPhoto
import com.example.advancedmovieddb.data.repository.MoviesRepository
import com.example.advancedmovieddb.ui.DetailsMoviesUiState
import com.example.advancedmovieddb.ui.MoviesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val repository: MoviesRepository

) : ViewModel() {

//    private val _moviesUi = MutableStateFlow(MoviesUiState())
//    val moviesUi: StateFlow<MoviesUiState> = _moviesUi.asStateFlow()

//    val state: StateFlow<UiState>
//    val pagingDataFlow : Flow<PagingData<MoviesPhoto>>
//
//    val accept: (UiAction) -> Unit


    suspend fun getPopularMovies(): Flow<PagingData<MoviesPhoto>> {
//        viewModelScope.launch {
//            val movies = getMoviesUseCase.invoke()
//            val moviesDeatiles = movies.results.map {
//                DetailsMoviesUiState(
//                    title = it.title,
//                    overview = it.overview,
//                    poster = it.posterPath
//                )
//            }
//            _moviesUi.update {
//                it.copy(
//                    moviesList = moviesDeatiles
//                )
//            }
//
//        }
        return repository.getMoviesRepo()
            .cachedIn(viewModelScope)
            .flowOn(Dispatchers.IO)
    }

}

//        getMovies()

//        val initialQuery: String = savedStateHandle.get(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
//        val lastQueryScrolled: String = savedStateHandle.get(LAST_QUERY_SCROLLED) ?: DEFAULT_QUERY
//        val actionStateFlow = MutableSharedFlow<UiAction>()
//        val searches = actionStateFlow
//            .filterIsInstance<UiAction.Search>()
//            .distinctUntilChanged()
//            .onStart { emit(UiAction.Search(query = initialQuery)) }
//        val queriesScrolled = actionStateFlow
//            .filterIsInstance<UiAction.Scroll>()
//            .distinctUntilChanged()
//            // This is shared to keep the flow "hot" while caching the last query scrolled,
//            // otherwise each flatMapLatest invocation would lose the last query scrolled,
//            .shareIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
//                replay = 1
//            )
//            .onStart { emit(UiAction.Scroll(currentQuery = lastQueryScrolled)) }
//
//        pagingDataFlow = searches
//            .flatMapLatest { getMoviesPaging() }
//            .cachedIn(viewModelScope)
//
//        state = combine(
//            searches,
//            queriesScrolled,
//            ::Pair
//        ).map { (search, scroll) ->
//            UiState(
//                query = search.query,
//                lastQueryScrolled = scroll.currentQuery,
//                // If the search query matches the scroll query, the user has scrolled
//                hasNotScrolledForCurrentSearch = search.query != scroll.currentQuery
//            )
//        }
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
//                initialValue = UiState()
//            )
//
//        accept = { action ->
//            viewModelScope.launch { actionStateFlow.emit(action) }
//        }
//
//    }
//
//     fun getMovies() {
//        viewModelScope.launch {
//            val movies = getMoviesUseCase.invoke()
//            val moviesDeatiles = movies.results.map {
//                DetailsMoviesUiState(
//                    title = it.title,
//                    overview = it.overview,
//                    poster = it.posterPath
//                )
//            }
//            _moviesUi.update {
//                it.copy(
//                    moviesList = moviesDeatiles
//                )
//            }
//
//        }
//    }
//
//    fun getMoviesPaging() : Flow<PagingData<MoviesPhoto>> =
//        repository.getMoviesRepo()
//
//}
//
//sealed class UiAction {
//    data class Search(val query: String) : UiAction()
//    data class Scroll(val currentQuery: String) : UiAction()
//}
//
//data class UiState(
//    val query: String = DEFAULT_QUERY,
//    val lastQueryScrolled: String = DEFAULT_QUERY,
//    val hasNotScrolledForCurrentSearch: Boolean = false
//)
//
//private const val LAST_QUERY_SCROLLED: String = "last_query_scrolled"
//private const val LAST_SEARCH_QUERY: String = "last_search_query"
//private const val DEFAULT_QUERY = "Android"