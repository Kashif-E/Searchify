package com.kashif.domain.models

import com.kashif.data.local.entities.MovieEntity
import com.kashif.data.network.models.MovieDTO
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class MovieMappingTests {

    @Test
    fun `MovieDTO asDomainModel maps fields correctly`() {

        //Arrange
        val dto = TestUtil.sampleMovieDTO

        // Act
        val domainModel = dto.asDomainModel()

        // Assert
        assertEquals(MovieDomainModel.getPosterPath(dto.poster_path), domainModel.posterPath)
        assertEquals("2021 Apr 05", domainModel.releaseDate)
        assertEquals("8.05", domainModel.popularity)
        assertEquals("9.04", domainModel.voteAverage)
        assertEquals(dto.vote_count.toString(), domainModel.voteCount)
        assertEquals(dto.video, domainModel.video)

    }

    @Test
    fun `MovieEntity asDomainModel maps fields correctly`() {

        //Arrange
        val entity = TestUtil.sampleMovieEntity

        // Act
        val domainModel = entity.asDomainModel()

        // Assert
        assertEquals(MovieDomainModel.getPosterPath(entity.poster_path), domainModel.posterPath)
        assertEquals("2021 Apr 05", domainModel.releaseDate)
        assertEquals("7.54", domainModel.popularity)
        assertEquals("8.57", domainModel.voteAverage)
        assertEquals(entity.vote_count.toString(), domainModel.voteCount)
        assertEquals(entity.video, domainModel.video)

    }
}

object TestUtil {

    val sampleMovieDTO = MovieDTO(
        page = 1,
        poster_path = "/path/to/poster.jpg",
        adult = true,
        overview = "Great movie",
        release_date =  "2021:04:05T20:23:23",
        genre_ids = listOf(16, 28),
        id = 101L,
        original_title = "A Sample Movie",
        original_language = "en",
        title = "Sample Movie",
        backdrop_path = "/path/to/backdrop.jpg",
        popularity = 8.054545f,
        vote_average = 9.043434f,
        vote_count = 200,
        video = true
    )


    val sampleMovieEntity = MovieEntity(
        page = 1,
        poster_path = "/path/to/poster.jpg",
        adult = false,
        overview = "Another great movie",
        release_date = "2021:04:05T20:23:23",
        genre_ids = listOf(16, 28),
        id = 102L,
        original_title = "Another Sample Movie",
        original_language = "en",
        title = "Another Movie",
        backdrop_path = "/path/to/backdrop.jpg",
        popularity = 7.543434f,
        vote_average = 8.56565f,
        vote_count = 150,
        video = false
    )
}

