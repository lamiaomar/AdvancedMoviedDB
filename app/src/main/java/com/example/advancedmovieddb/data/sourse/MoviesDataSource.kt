package com.example.advancedmovieddb.data.sourse

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.advancedmovieddb.data.MoviesApiService
import com.example.advancedmovieddb.data.api.model.Movies
import com.example.advancedmovieddb.data.api.model.MoviesPhoto
import com.example.advancedmovieddb.data.repository.MoviesRepository.Companion.NETWORK_PAGE_SIZE
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

private const val MOVIES_STARTING_PAGE_INDEX = 1


class MoviesDataSource @Inject constructor(
    private val moviesApiService : MoviesApiService ,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    ) : PagingSource<Int, MoviesPhoto>() {

    suspend fun getMovies() : Movies =
        withContext(ioDispatcher){
            moviesApiService.getMovies(1,18)
        }

    override fun getRefreshKey(state: PagingState<Int, MoviesPhoto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesPhoto> {
        val position = params.key ?: MOVIES_STARTING_PAGE_INDEX
        return try {
            val response = moviesApiService.getMovies(position , params.loadSize)
            val repos = response.results
            val nextKey  = if (repos.isEmpty()){
                null
            }else{
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = repos ,
                prevKey = if (position == MOVIES_STARTING_PAGE_INDEX) null else position - 1 ,
                nextKey = nextKey?.plus(1)
            )
        }catch (e : Exception){
            Log.e("exceptionData", "$e")
            return LoadResult.Error(e)
        }
    }

}