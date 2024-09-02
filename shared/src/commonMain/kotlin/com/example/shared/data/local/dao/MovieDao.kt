package com.example.shared.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.shared.data.local.entities.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(movieEntities: List<MovieEntity>)

    @Update
    suspend fun updateMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM MOVIEENTITY WHERE id = :id_")
    suspend fun getMovie(id_: Long): MovieEntity

    @Query("SELECT * FROM MovieEntity WHERE page = :page_")
    suspend fun getMovieList(page_: Int): List<MovieEntity>
}
