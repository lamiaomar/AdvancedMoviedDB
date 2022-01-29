package com.example.advancedmovieddb.di

import com.example.advancedmovieddb.data.MoviesApi
import com.example.advancedmovieddb.data.MoviesApiService
import com.example.advancedmovieddb.data.repository.MoviesRepository
import com.example.advancedmovieddb.data.sourse.MoviesDataSource
import com.example.advancedmovieddb.domain.GetMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModel {

    @Provides
    fun provideMoviesApi() : MoviesApiService
    = MoviesApi.retrofitService

    @Provides
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun provideMoviesDataSource(moviesApiService: MoviesApiService ) : MoviesDataSource
    = MoviesDataSource(moviesApiService , provideDispatcher())

    @Provides
    @Singleton
    fun provideMoviesRepo(moviesDataSource: MoviesDataSource ,moviesApiService: MoviesApiService) : MoviesRepository
    = MoviesRepository(moviesDataSource , moviesApiService)

    @Provides
    @Singleton
    fun provideGetMoviesUseCase(moviesRepository: MoviesRepository) : GetMoviesUseCase
    = GetMoviesUseCase(moviesRepository)
}

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
