package com.kashif.feature_search.data.repository

import androidx.annotation.WorkerThread
import com.kashif.domain.models.MovieDomainModel
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    @WorkerThread
    fun getMovies(page : Int): Flow<List<MovieDomainModel>>

}