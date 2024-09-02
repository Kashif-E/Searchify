package com.example.shared.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.shared.data.local.converters.IntegerListConverter
import com.example.shared.data.local.dao.MovieDao
import com.example.shared.data.local.entities.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1, exportSchema = false
)
@TypeConverters(
    value = [
        (IntegerListConverter::class),
    ]
)
abstract class MovieDB : RoomDatabase() {
    abstract fun movieDao(): MovieDao

}

expect fun provideDatabase(context: Any): MovieDB