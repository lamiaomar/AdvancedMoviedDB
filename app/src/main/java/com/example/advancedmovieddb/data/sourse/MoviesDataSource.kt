package com.example.advancedmovieddb.data.sourse

import com.example.advancedmovieddb.data.MoviesApiService
import com.example.advancedmovieddb.model.Movies
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import javax.inject.Inject

class MoviesDataSource @Inject constructor(
    private val moviesApiService : MoviesApiService ,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    ){

    suspend fun getMovies() : Movies =
        withContext(ioDispatcher){
            moviesApiService.getMovies()
        }

}