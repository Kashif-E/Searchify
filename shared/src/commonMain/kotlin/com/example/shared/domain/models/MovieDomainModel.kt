package com.example.shared.domain.models

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

        fun getPosterPath(posterPath: String?): String {
            return BASE_POSTER_PATH + posterPath
        }

        fun getBackdropPath(backdropPath: String?): String {
            return BASE_BACKDROP_PATH + backdropPath
        }
//todo fix this later
        fun parseAndFormatDateTime(inputDate: String?): String? {
            return inputDate
//            return try {
//                val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
//                val parsedDate = inputDate?.let { inputFormat.parse(it) }
//
//                val outputFormat = SimpleDateFormat("MMM-yyyy", Locale.US)
//                parsedDate?.let { outputFormat.format(it) }
//            } catch (e: Exception) {
//                e.printStackTrace()
//                null
//            }
        }

        fun formatFloat(number: Float) = number.toString()


    }

}


