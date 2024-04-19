package com.kashif.data.local.entities

import androidx.room.Entity


@Entity(primaryKeys = [("id")])
data class MovieEntity(
    var page: Int,
    val poster_path: String?,
    val adult: Boolean,
    val overview: String,
    val releaseDate: String?,
    val genreIds: List<Int>,
    val id: Long,
    val originalTitle: String,
    val originalLanguage: String,
    val title: String,
    val backdropPath: String?,
    val popularity: Float,
    val voteCount: Int,
    val video: Boolean,
    val voteAverage: Float
)
