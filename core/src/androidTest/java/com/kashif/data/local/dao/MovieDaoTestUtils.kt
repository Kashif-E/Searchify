package com.kashif.data.local.dao

import com.kashif.data.local.entities.MovieEntity

object MovieDaoTestUtils {

    val sampleMovie = MovieEntity(
        page = 1,
        poster_path = "https://image.tmdb.org/t/p/w500/path_to_poster.jpg",
        adult = false,
        overview = "A gripping action thriller about a rogue detective who seeks justice.",
        releaseDate = "2022-12-15",
        genreIds = listOf(28, 53, 80),
        id = 550987L,
        originalTitle = "Night Hunter",
        originalLanguage = "en",
        title = "Night Hunter",
        backdropPath = "https://image.tmdb.org/t/p/w500/backdrop_path.jpg",
        popularity = 78.5f,
        voteCount = 1342,
        video = false,
        voteAverage = 7.9f
    )

    val moviesList = listOf(
        sampleMovie,
        sampleMovie.copy(id = 550988, title = "Day Hunter", originalTitle = "Day Hunter"),
        sampleMovie.copy(id = 550989, title = "Dawn Hunter", originalTitle = "Dawn Hunter"),
        sampleMovie.copy(id = 550990, title = "Dusk Hunter", originalTitle = "Dusk Hunter")
    )
}
