package com.example.advancedmovieddb.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.advancedmovieddb.data.MoviesApiService
import com.example.advancedmovieddb.data.sourse.MoviesDataSource
import com.example.advancedmovieddb.data.api.model.Movies
import com.example.advancedmovieddb.data.api.model.MoviesPhoto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesDataSource: MoviesDataSource,
    private val moviesApiService : MoviesApiService,
        ){

    suspend fun getMovies() : Movies =
        moviesDataSource.getMovies()

    fun getMoviesRepo() : Flow<PagingData<MoviesPhoto>>{
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                initialLoadSize = 40
            ),
            pagingSourceFactory = {MoviesDataSource(moviesApiService)}
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 1000
    }

}