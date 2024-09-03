package com.example.shared.di

import com.example.shared.data.local.MovieDB
import com.example.shared.data.local.dao.MovieDao
import com.example.shared.data.local.provideDatabase
import org.koin.core.annotation.Single


@Single
fun provideRoomDataBase(): MovieDB {
    //todo
    return provideDatabase(Any())
}

@Single
fun provideMovieDao(movieDB: MovieDB): MovieDao {
    return movieDB.movieDao()
}

