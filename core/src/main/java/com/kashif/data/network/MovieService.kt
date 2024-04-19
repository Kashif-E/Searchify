package com.kashif.data.network

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    /**
     * Searches for movies based on a user query.
     * <p>
     * This method retrieves search results from The Movie Database (TMDb). It supports pagination to browse through multiple results.
     * For more details, visit the official API documentation at:
     * <a href="https://developers.themoviedb.org/3/search/search-movies">Search Movies API</a>.
     * </p>
     *
     * @param [query] The search query string used to look for movies.
     * @param [page]  The page number for pagination of results.
     * @return [], which includes the list of movies matching the query and other metadata like total results and number of pages.
     */
    @GET("search/movie")
    suspend fun searchMovies(@Query("query") query: String, @Query("page") page: Int): Flow<Result<String>>
}

