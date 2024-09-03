package com.example.shared.data.repository

import androidx.annotation.WorkerThread
import com.example.shared.domain.models.MovieDomainModel
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    @WorkerThread
    fun getMovies(page : Int): Flow<List<MovieDomainModel>>

}