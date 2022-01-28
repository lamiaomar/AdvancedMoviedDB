package com.example.advancedmovieddb.ui.movies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.advancedmovieddb.data.MoviesApi
import com.example.advancedmovieddb.data.MoviesApiService
import com.example.advancedmovieddb.data.repository.MoviesRepository
import com.example.advancedmovieddb.data.sourse.MoviesDataSource
import com.example.advancedmovieddb.domain.GetMoviesUseCase
//
//class MoviesViewModelFactory() : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)){
//            return MoviesViewModel(tempServiceLocater.provideUseCase()) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
//
//object tempServiceLocater{
//    fun provideMoviesApi() : MoviesApiService = MoviesApi.retrofitService
//
//    fun provideDataSource() : MoviesDataSource = MoviesDataSource(provideMoviesApi())
//
//    fun provideRepo() : MoviesRepository = MoviesRepository(provideDataSource())
//
//    fun provideUseCase() : GetMoviesUseCase = GetMoviesUseCase(provideRepo())
//
//
//}
