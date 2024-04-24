package com.kashif.feature_search.di

import com.kashif.data.local.dao.MovieDao
import com.kashif.feature_search.data.remote.MovieService
import com.kashif.feature_search.data.repository.IMovieRepository
import com.kashif.feature_search.data.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FeatureMoviesModule {

    @Provides
    @Singleton
    fun providesMovieService(retrofit: Retrofit): MovieService{
        return retrofit.create(MovieService::class.java)
    }

    @Provides
    @Singleton
    fun providesMoviesRepository(movieService: MovieService, movieDao: MovieDao): MovieRepository{
        return MovieRepository(movieDao = movieDao, movieService=movieService)
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