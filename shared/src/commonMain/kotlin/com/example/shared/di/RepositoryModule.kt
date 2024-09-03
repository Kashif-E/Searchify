package com.example.shared.di

import com.example.shared.data.local.dao.MovieDao
import com.example.shared.data.remote.RemoteDataSource
import com.example.shared.data.repository.IMovieRepository
import com.example.shared.data.repository.MovieRepository
import org.koin.core.annotation.Single


@Single(binds = [IMovieRepository::class])
fun providesMoviesRepository(
    movieService: RemoteDataSource,
    movieDao: MovieDao
): MovieRepository {
    return MovieRepository(movieDao = movieDao, movieService = movieService)
}
