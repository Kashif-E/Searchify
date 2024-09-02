package com.kashif.domain.di


import android.content.Context
import com.example.shared.data.local.MovieDB
import com.example.shared.data.local.dao.MovieDao
import com.example.shared.data.local.provideDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    const val MovieDBName = "SearchifyMovie.db"

    @Provides
    @Singleton
    fun provideRoomDataBase(@ApplicationContext context: Context): MovieDB {
        return provideDatabase(context)
    }

    @Provides
    @Singleton
    fun provideMovieDao(movieDB: MovieDB): MovieDao {
        return movieDB.movieDao()
    }

}
