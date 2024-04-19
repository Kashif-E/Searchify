package com.kashif.data.network


object Api {
    const val BASE_URL = "https://api.themoviedb.org/3"
    private const val BASE_POSTER_PATH = "https://image.tmdb.org/t/p/w342"
    private const val BASE_BACKDROP_PATH = "https://image.tmdb.org/t/p/w780"
    private const val YOUTUBE_VIDEO_URL = "https://www.youtube.com/watch?v="
    private const val YOUTUBE_THUMBNAIL_URL = "https://img.youtube.com/vi/"
    const val PAGING_SIZE = 20

    @JvmStatic
    fun getPosterPath(posterPath: String?): String {
        return BASE_POSTER_PATH + posterPath
    }

    @JvmStatic
    fun getBackdropPath(backdropPath: String?): String {
        return BASE_BACKDROP_PATH + backdropPath
    }

    @JvmStatic
    fun getYoutubeVideoPath(videoPath: String?): String {
        return YOUTUBE_VIDEO_URL + videoPath
    }

    @JvmStatic
    fun getYoutubeThumbnailPath(thumbnailPath: String?): String {
        return "$YOUTUBE_THUMBNAIL_URL$thumbnailPath/default.jpg"
    }
}
