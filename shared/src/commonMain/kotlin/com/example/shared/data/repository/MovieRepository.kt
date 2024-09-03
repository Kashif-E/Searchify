package com.example.shared.data.repository


import com.example.shared.data.remote.RemoteDataSource
import com.example.shared.data.remote.models.asDomainModel
import com.example.shared.domain.models.MovieDomainModel
import com.example.shared.data.local.dao.MovieDao
import com.example.shared.data.local.entities.asDomainModel
import com.example.shared.data.local.entities.asEntity
import com.example.shared.domain.utils.suspendMap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class MovieRepository(
    private val movieDao: MovieDao, private val movieService: RemoteDataSource
) : IMovieRepository {

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

