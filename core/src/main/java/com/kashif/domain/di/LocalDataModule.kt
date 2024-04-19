package com.kashif.domain.di


import android.content.Context
import androidx.room.Room
import com.kashif.data.local.MovieDB
import com.kashif.data.local.dao.MovieDao
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
        return Room
            .databaseBuilder(context, MovieDB::class.java, MovieDBName)
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(movieDB: MovieDB): MovieDao {
        return movieDB.movieDao()
    }

}
