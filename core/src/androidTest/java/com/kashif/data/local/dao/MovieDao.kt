package com.kashif.data.local.dao

import androidx.test.filters.SmallTest
import com.kashif.data.local.MovieDB
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertIterableEquals
import org.junit.jupiter.api.assertAll
import javax.inject.Inject

/**
 * Tests MovieDao operations for accuracy and proper data handling, including CRUD operations and Converters.
 */

@HiltAndroidTest
@SmallTest
class MovieDaoTest {
    @get:Rule
    val rule = HiltAndroidRule(this)


    @Inject
    lateinit var movieDB: MovieDB


    private lateinit var movieDao: MovieDao

    @Before
    fun setup() {
        rule.inject()
        movieDao = movieDB.movieDao()
    }

    @Test
    fun insertMoviesItem() = runTest {
        // Arrange
        val sampleMovie = MovieDaoTestUtils.sampleMovie
        movieDao.insertMovieList(listOf(sampleMovie))

        // Act
        val result = movieDao.getMovie(sampleMovie.id)

        // Assert
        assert(result == sampleMovie)
    }



    @Test
    fun verifyTypeConverters() = runTest {
        //Arrange
        movieDao.insertMovieList(MovieDaoTestUtils.moviesList)

        //Act
        val results = movieDao.getMovieList(1)

        //Assert
        assertAll("Verify each movie and its genres",
            results.map { movie ->
                {
                    val expectedGenres =
                        MovieDaoTestUtils.moviesList.find { it.id == movie.id }?.genre_ids
                    assertIterableEquals(
                        expectedGenres,
                        movie.genre_ids,
                        "Genres should match for movie ID: ${movie.id}"
                    )
                }
            }
        )
    }

    @Test
    fun verifyUpdateMovie() = runTest {
        // Arrange
        movieDao.insertMovieList(MovieDaoTestUtils.moviesList)
        val movieToBeUpdated = MovieDaoTestUtils.moviesList.first().copy(title = "New Title")

        // Act
        movieDao.updateMovie(movieToBeUpdated)

        // Assert
        val updatedMovie = movieDao.getMovieList(1).first()
        assert(updatedMovie == movieToBeUpdated)
    }

}