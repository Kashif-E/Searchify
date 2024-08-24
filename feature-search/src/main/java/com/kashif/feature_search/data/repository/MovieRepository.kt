package com.kashif.feature_search.data.repository

import androidx.annotation.WorkerThread
import com.example.shared.data.remote.RemoteDataSource
import com.example.shared.data.remote.models.asDomainModel
import com.example.shared.domain.models.MovieDomainModel
import com.kashif.data.local.dao.MovieDao
import com.kashif.data.local.entities.asDomainModel
import com.kashif.data.local.entities.asEntity
import com.kashif.domain.utils.suspendMap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieDao: MovieDao, private val movieService: RemoteDataSource
) : IMovieRepository {
    @WorkerThread
    override fun getMovies(page: Int): Flow<List<MovieDomainModel>> = flow {
        val localMovies = movieDao.getMovieList(page)
        if (localMovies.isEmpty()) {
            val response = movieService.getMovies()

            val movies = response.results
            movies.suspendMap { it.copy(page = response.page) }
            movieDao.insertMovieList(movies.asEntity())
            emit(movies.asDomainModel())

        } else {
            emit(localMovies.asDomainModel())
        }
    }
}

