package com.kashif.data.local.entities

import androidx.room.Entity
import com.example.shared.data.remote.models.MovieDTO
import com.example.shared.domain.models.MovieDomainModel

import com.kashif.domain.utils.suspendMap


@Entity(primaryKeys = [("id")])
data class MovieEntity(
    var page: Int,
    val poster_path: String?,
    val adult: Boolean,
    val overview: String,
    val release_date: String?,
    val genre_ids: List<Int>,
    val id: Long,
    val original_title: String,
    val original_language: String,
    val title: String,
    val backdrop_path: String?,
    val popularity: Float,
    val vote_count: Int,
    val video: Boolean,
    val vote_average: Float
)

fun MovieEntity.asDomainModel() = MovieDomainModel(
    page = this.page, posterPath = MovieDomainModel.getPosterPath(this.poster_path),
    adult = this.adult,
    overview = this.overview,
    releaseDate = release_date?.let { MovieDomainModel.parseAndFormatDateTime(release_date) }
        ?: run { "" },
    genreIds = this.genre_ids,
    id = this.id,
    originalTitle = this.original_title,
    originalLanguage = this.original_language,
    title = this.title,
    backdropPath = MovieDomainModel.getBackdropPath(backdrop_path),
    popularity = MovieDomainModel.formatFloat(this.popularity),
    voteAverage = MovieDomainModel.formatFloat(this.vote_average),
    voteCount = this.vote_count.toString(),
    video = video

)

suspend fun List<MovieEntity>.asDomainModel() = suspendMap { movieEntity -> movieEntity.asDomainModel() }



fun MovieDTO.asEntity() = MovieEntity(
    this.page,
    poster_path,
    adult,
    overview,
    release_date,
    genre_ids,
    id,
    original_title,
    original_language,
    title,
    backdrop_path,
    popularity,
    vote_count,
    video,
    vote_average
)

suspend fun List<MovieDTO>.asEntity() = map { it.asEntity() }
