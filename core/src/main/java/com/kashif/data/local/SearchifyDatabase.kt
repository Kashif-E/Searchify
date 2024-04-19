package com.kashif.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kashif.data.local.converters.IntegerListConverter
import com.kashif.data.local.dao.MovieDao
import com.kashif.data.local.entities.MovieEntity

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
