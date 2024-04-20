package com.kashif.domain.models

import com.kashif.data.local.entities.MovieEntity
import com.kashif.data.network.models.MovieDTO
import java.text.SimpleDateFormat
import java.util.Locale
import javax.annotation.concurrent.Immutable


@Immutable
data class MovieDomainModel(
    var page: Int,
    val posterPath: String?,
    val adult: Boolean,
    val overview: String,
    val releaseDate: String?,
    val genreIds: List<Int>,
    val id: Long,
    val originalTitle: String,
    val originalLanguage: String,
    val title: String,
    val backdropPath: String?,
    val popularity: String,
    val voteCount: String,
    val video: Boolean,
    val voteAverage: String
) {
    companion object {
        private const val BASE_POSTER_PATH = "https://image.tmdb.org/t/p/w342"
        private const val BASE_BACKDROP_PATH = "https://image.tmdb.org/t/p/w780"
        private const val YOUTUBE_VIDEO_URL = "https://www.youtube.com/watch?v="
        private const val YOUTUBE_THUMBNAIL_URL = "https://img.youtube.com/vi/"


        fun getPosterPath(posterPath: String?): String {
            return BASE_POSTER_PATH + posterPath
        }

        fun getBackdropPath(backdropPath: String?): String {
            return BASE_BACKDROP_PATH + backdropPath
        }

        fun getYoutubeVideoPath(videoPath: String?): String {
            return YOUTUBE_VIDEO_URL + videoPath
        }

        fun getYoutubeThumbnailPath(thumbnailPath: String?): String {
            return "$YOUTUBE_THUMBNAIL_URL$thumbnailPath/default.jpg"
        }

        fun parseAndFormatDateTime(isoDateTime: String?): String? {
            val inputFormat = SimpleDateFormat("yyyy:MM:dd'T'HH:mm:ss", Locale.ENGLISH)

            val outputFormat = SimpleDateFormat("yyyy MMM dd", Locale.ENGLISH)
            return try {

                val date = isoDateTime?.let { inputFormat.parse(it) } ?: run { return null }

                outputFormat.format(date)

            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

        fun formatFloat(number: Float) = String.format("%.2f", number)


    }

}

fun MovieDTO.asDomainModel() = MovieDomainModel(
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
    backdropPath = this.backdrop_path,
    popularity = MovieDomainModel.formatFloat(this.popularity),
    voteAverage = MovieDomainModel.formatFloat(this.vote_average),
    voteCount = this.vote_count.toString(),
    video = video

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
    backdropPath = this.backdrop_path,
    popularity = MovieDomainModel.formatFloat(this.popularity),
    voteAverage = MovieDomainModel.formatFloat(this.vote_average),
    voteCount = this.vote_count.toString(),
    video = video

)

