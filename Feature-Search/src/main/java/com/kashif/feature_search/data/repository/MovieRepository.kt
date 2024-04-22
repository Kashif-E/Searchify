package com.kashif.feature_search.data.repository

import androidx.annotation.WorkerThread
import com.kashif.data.local.dao.MovieDao
import com.kashif.data.local.entities.asDomainModel
import com.kashif.data.network.models.asDomainModel
import com.kashif.data.network.models.asEntity
import com.kashif.domain.models.MovieDomainModel
import com.kashif.feature_search.data.remote.MovieService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieDao: MovieDao, private val movieService: MovieService
) : IMovieRepository {
    @WorkerThread
    override fun getMovies(page: Int): Flow<List<MovieDomainModel>> = flow {
        val localMovies = movieDao.getMovieList(page)
        if (localMovies.isEmpty()) {
            val response = movieService.getMovies(page)
            if (response.isSuccessful && response.body() != null) {
                val movies = response.body()!!.results
                movies.map { it.copy(page = response.body()!!.page) }
                movieDao.insertMovieList(movies.asEntity())
                emit(movies.asDomainModel())
            } else {
                throw Exception(response.message())
            }
        } else {
            emit(localMovies.asDomainModel())
        }
    }
}

