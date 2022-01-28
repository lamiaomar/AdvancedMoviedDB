package com.example.advancedmovieddb.domain

import com.example.advancedmovieddb.data.repository.MoviesRepository
import com.example.advancedmovieddb.model.Movies

class GetMoviesUseCase(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke():Movies =
        moviesRepository.getMovies()

}