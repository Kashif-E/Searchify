package com.kashif.domain.di

import android.content.Context
import androidx.room.Room
import com.kashif.data.local.MovieDB
import com.kashif.data.local.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [LocalDataModule::class]
)
object TestDatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MovieDB = Room.inMemoryDatabaseBuilder(
        context, MovieDB::class.java
    ).allowMainThreadQueries().build()

    @Provides
    @Singleton
    fun provideMovieDao(movieDB: MovieDB): MovieDao {
        return movieDB.movieDao()
    }
}