package com.kashif.data.local.dao

import com.example.shared.data.local.entities.MovieEntity

object MovieDaoTestUtils {

    val sampleMovie = MovieEntity(
        page = 1,
        poster_path = "https://image.tmdb.org/t/p/w500/path_to_poster.jpg",
        adult = false,
        overview = "A gripping action thriller about a rogue detective who seeks justice.",
        release_date = "2022-12-15",
        genre_ids = listOf(28, 53, 80),
        id = 550987L,
        original_title = "Night Hunter",
        original_language = "en",
        title = "Night Hunter",
        backdrop_path = "https://image.tmdb.org/t/p/w500/backdrop_path.jpg",
        popularity = 78.5f,
        vote_count = 1342,
        video = false,
        vote_average = 7.9f
    )

    val moviesList = listOf(
        sampleMovie,
        sampleMovie.copy(id = 550988, title = "Day Hunter", original_title = "Day Hunter"),
        sampleMovie.copy(id = 550989, title = "Dawn Hunter", original_title = "Dawn Hunter"),
        sampleMovie.copy(id = 550990, title = "Dusk Hunter", original_title = "Dusk Hunter")
    )
}
