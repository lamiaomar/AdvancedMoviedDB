package com.example.advancedmovieddb.ui.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.advancedmovieddb.databinding.FragmentMoviesBinding
import com.example.advancedmovieddb.ui.movies.adapter.LoadingStateAdapter
import com.example.advancedmovieddb.ui.movies.adapter.MoviesAdapter
import com.example.advancedmovieddb.ui.movies.viewModel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private val viewModel: MoviesViewModel by viewModels()

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var adapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.photosGrid.adapter = MoviesAdapter()

        configureList()
        configureStateListener()
        fetchMovies()

        binding.retryButton.setOnClickListener { adapter.retry() }


        return binding.root

    }

    private fun configureList() {
        binding.photosGrid.layoutManager = GridLayoutManager(view?.context, 2)
        adapter = MoviesAdapter()
        binding.photosGrid.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoadingStateAdapter { adapter.retry() },
            footer = LoadingStateAdapter { adapter.retry() }
        )
    }

    private fun fetchMovies() {
        lifecycleScope.launch {
            viewModel.getPopularMovies().collectLatest {
                Log.e("flow1","${viewModel.getPopularMovies()}")
                adapter.submitData(it)
            }
        }
    }

    private fun configureStateListener() {
        adapter.addLoadStateListener { loadState ->
            configureViews(loadState)

            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error

            errorState?.let {
                Toast.makeText(activity, errorState.error.localizedMessage, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun configureViews(loadState: CombinedLoadStates) {
        when ( loadState.source.refresh) {
            is LoadState.NotLoading -> {
                binding.photosGrid.isVisible = true
                binding.progressBar.isVisible =false
                binding.retryButton.isVisible = false
                if(adapter.itemCount<1){
                    binding.retryButton.isVisible=true
                }
            }
            is LoadState.Loading -> {
                binding.progressBar.isVisible = true
                binding.retryButton.isVisible = false
            }
            is LoadState.Error -> {
                binding.photosGrid.isVisible = false
                binding.progressBar.isVisible = false
                binding.retryButton.isVisible = true
            }
        }
    }


}





//    private fun FragmentMoviesBinding.bindState(
//        uiState: StateFlow<UiState>,
//        pagingData: Flow<PagingData<MoviesPhoto>>,
//        uiActions: (UiAction) -> Unit
//    ) {
//        val repoAdapter = MoviesAdapter()
//        photosGrid.adapter = repoAdapter
////        withLoadStateHeaderAndFooter(
//////        header = ReposLoadStateAdapter { repoAdapter.retry() },
//////        footer = ReposLoadStateAdapter { repoAdapter.retry() }
////        )
//
//        binding.retryButton.setOnClickListener { adapter.retry() }
//
//        bindList(
//            repoAdapter = repoAdapter,
//            uiState = uiState,
//            pagingData = pagingData,
//            onScrollChanged = uiActions
//        )
//    }


//class ReposLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<ReposLoadStateViewHolder>() {
//    override fun onBindViewHolder(holder: ReposLoadStateViewHolder, loadState: LoadState) {
//        holder.bind(loadState)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ReposLoadStateViewHolder {
//        return ReposLoadStateViewHolder.create(parent, retry)
//    }
//}


//    private fun FragmentMoviesBinding.bindList(
//        repoAdapter: MoviesAdapter,
//        uiState: StateFlow<UiState>,
//        pagingData: Flow<PagingData<MoviesPhoto>>,
//        onScrollChanged: (UiAction.Scroll) -> Unit
//    ) {
//
////    retryButton.setOnClickListener { repoAdapter.retry() }
//
//        photosGrid.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                if (dy != 0) onScrollChanged(UiAction.Scroll(currentQuery = uiState.value.query))
//            }
//        })
//        val notLoading = repoAdapter.loadStateFlow
//            // Only emit when REFRESH LoadState for the paging source changes.
//            .distinctUntilChangedBy { it.source.refresh }
//            // Only react to cases where REFRESH completes i.e., NotLoading.
//            .map { it.source.refresh is LoadState.NotLoading }
//
//        val hasNotScrolledForCurrentSearch = uiState
//            .map { it.hasNotScrolledForCurrentSearch }
//            .distinctUntilChanged()
//
//        val shouldScrollToTop = combine(
//            notLoading,
//            hasNotScrolledForCurrentSearch,
//            Boolean::and
//        )
//            .distinctUntilChanged()
//
//        lifecycleScope.launch {
////            pagingData.collectLatest(repoAdapter::submitData)
//        }
//
//        lifecycleScope.launch {
//            repoAdapter.loadStateFlow.collect { loadState ->
//                val isListEmpty =
//                    loadState.refresh is LoadState.NotLoading && repoAdapter.itemCount == 0
//                // show empty list
////                emptyList.isVisible = isListEmpty
//                // Only show the list if refresh succeeds.
//                photosGrid.isVisible = !isListEmpty
//                // Show loading spinner during initial load or refresh.
////                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
//                // Show the retry state if initial load or refresh fails.
////                retryButton.isVisible = loadState.source.refresh is LoadState.Error
//
//                // Toast on any error, regardless of whether it came from RemoteMediator or PagingSource
//                val errorState = loadState.source.append as? LoadState.Error
//                    ?: loadState.source.prepend as? LoadState.Error
//                    ?: loadState.append as? LoadState.Error
//                    ?: loadState.prepend as? LoadState.Error
//                errorState?.let {
//                    Toast.makeText(
//                        context,
//                        "\uD83D\uDE28 Wooops ${it.error}",
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//            }
//        }
//    }
