package com.kashif.feature_search.di

import com.example.shared.data.remote.RemoteDataSource
import com.kashif.data.local.dao.MovieDao
import com.kashif.feature_search.data.repository.IMovieRepository
import com.kashif.feature_search.data.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FeatureMoviesModule {



    @Provides
    @Singleton
    fun providesMoviesRepository(
        movieService: RemoteDataSource,
        movieDao: MovieDao
    ): MovieRepository {
        return MovieRepository(movieDao = movieDao, movieService = movieService)
    }


}

@Module
@InstallIn(SingletonComponent::class)
internal interface MoviesRepositoryModule {
    @Binds
    fun bindsMovieRepository(
        movieRepository: MovieRepository,
    ): IMovieRepository
}