package com.kashif.data.local.entities

import androidx.room.Entity
import com.kashif.domain.models.MovieDomainModel


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

fun List<MovieEntity>.asDomainModel() = map { movieEntity -> movieEntity.asDomainModel() }
