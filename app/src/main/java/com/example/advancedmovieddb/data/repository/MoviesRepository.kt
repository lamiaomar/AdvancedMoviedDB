package com.example.advancedmovieddb.data.repository

import com.example.advancedmovieddb.data.sourse.MoviesDataSource
import com.example.advancedmovieddb.model.Movies

class MoviesRepository (
    private val moviesDataSource: MoviesDataSource
        ){

    suspend fun getMovies() : Movies =
        moviesDataSource.getMovies()

}