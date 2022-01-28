package com.example.advancedmovieddb.data.repository

import com.example.advancedmovieddb.data.sourse.MoviesDataSource
import com.example.advancedmovieddb.model.Movies
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesDataSource: MoviesDataSource
        ){

    suspend fun getMovies() : Movies =
        moviesDataSource.getMovies()

}