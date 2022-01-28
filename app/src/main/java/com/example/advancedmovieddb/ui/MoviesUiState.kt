package com.example.advancedmovieddb.ui



data class MoviesUiState (
    val moviesList : List<DetailsMoviesUiState> = listOf()
        )

data class DetailsMoviesUiState (
    val overview: String = "",
    val title : String = "",
    val poster : String = ""
    )