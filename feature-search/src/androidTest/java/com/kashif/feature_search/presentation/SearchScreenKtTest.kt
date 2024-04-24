/*
package com.kashif.feature_search.presentation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kashif.domain.models.MovieDomainModel
import com.kashif.feature_search.data.repository.IMovieRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import java.net.URLDecoder
import java.net.URLEncoder

class SearchScreenKtTest {

    @get:Rule
    val rule = createComposeRule<Main>()

    @OptIn(ExperimentalSharedTransitionApi::class)
    @Test
    fun testSearchScreen() {
        val viewModel = mockk<MoviesViewModel>()

        every { viewModel.searchMoviesState } returns MutableStateFlow(VMTestUtils.sampleMovies)
        every { viewModel.moviePagingState } returns MutableStateFlow(PagingState.Success)
        every { viewModel.moviesState } returns MutableStateFlow(VMTestUtils.sampleMovies)
        every { viewModel.searchQuery } returns MutableStateFlow("")
        every { viewModel.getCurrentPage() } returns 1
        every { viewModel.onEvent(UIEvent.FetchNextPage) } returns Unit


        rule.setContent {
            val navController = rememberNavController()

            SharedTransitionLayout(

            ) {
                NavHost(
                    navController = navController,
                    startDestination = Route.List.route,
                    modifier = Modifier.fillMaxSize(),
                    enterTransition = { slideInHorizontally { it } + fadeIn() },
                    exitTransition = { slideOutHorizontally { -it } + fadeOut() },
                    popEnterTransition = { slideInHorizontally { -it } + fadeIn() },
                    popExitTransition = { slideOutHorizontally { it } + fadeOut() },
                ) {

                    composable(
                        route = Route.List.route
                    ) {

                        SearchScreen(
                            viewModel = viewModel,
                            animatedVisibilityScope = this@composable,
                            onItemClick = { item ->
                                navController.navigate("details/${item.utf8()}")
                            },
                        )
                    }
                    composable(
                        route = Route.Details.route
                    ) { backstackEntry ->

                        val url = backstackEntry.getParameter("url")
                        DetailsScreen(
                            url = url,
                            animatedVisibilityScope = this@composable,
                            onClick = { navController.popBackStack() },
                            modifier = Modifier.fillMaxSize(),
                            moviesViewModel = viewModel
                        )
                    }
                }
            }
        }
        rule.onNodeWithText("Day Hunter").assertExists()
    }

}

fun NavBackStackEntry.getParameter(key: String): String {
    val encoded = arguments?.getString(key) ?: error("No $key found")
    return URLDecoder.decode(encoded, "UTF-8")
}

fun String.utf8(): String {
    return URLEncoder.encode(this, "UTF-8")
}

object VMTestUtils {

    val sampleMovie = MovieDomainModel(
        page = 1,
        posterPath = "https://image.tmdb.org/t/p/w500/path_to_poster.jpg",
        adult = false,
        overview = "A gripping action thriller about a rogue detective who seeks justice.",
        releaseDate = "2022-12-15",
        genreIds = listOf(28, 53, 80),
        id = 550987L,
        originalTitle = "Night Hunter",
        originalLanguage = "en",
        title = "Night Hunter",
        backdropPath = "https://image.tmdb.org/t/p/w500/backdrop_path.jpg",
        popularity = "78.5",
        voteCount = "1342",
        video = false,
        voteAverage = "7.9"
    )

    val sampleMovies = listOf(
        sampleMovie,
        sampleMovie.copy(
            id = 550988L,
            title = "Day Hunter",
            originalTitle = "Day Hunter",
            releaseDate = "2023-01-20",
            overview = "A thrilling sequel to Night Hunter."
        ),
        sampleMovie.copy(
            id = 550989L,
            title = "Dawn Hunter",
            originalTitle = "Dawn Hunter",
            releaseDate = "2023-05-10",
            overview = "The prequel story of Night Hunter."
        ),
        sampleMovie.copy(
            id = 550990L,
            title = "Dusk Hunter",
            originalTitle = "Dusk Hunter",
            releaseDate = "2023-11-18",
            overview = "The final chapter in the Hunter series."
        ),
        sampleMovie.copy(
            id = 550991L,
            title = "Twilight Hunter",
            originalTitle = "Twilight Hunter",
            releaseDate = "2024-03-22",
            overview = "The Hunter saga continues in the twilight of the world."
        ),
        sampleMovie.copy(
            id = 550992L,
            title = "Midnight Hunter",
            originalTitle = "Midnight Hunter",
            releaseDate = "2024-08-15",
            overview = "The darkest hour for the Hunter."
        ),
        sampleMovie.copy(
            id = 550993L,
            title = "Morning Hunter",
            originalTitle = "Morning Hunter",
            releaseDate = "2025-02-01",
            overview = "The beginning of the Hunter's journey at dawn."
        ),
        sampleMovie.copy(
            id = 550994L,
            title = "Afternoon Hunter",
            originalTitle = "Afternoon Hunter",
            releaseDate = "2025-07-12",
            overview = "The Hunter's quest continues under the afternoon sun."
        ),
        sampleMovie.copy(
            id = 550995L,
            title = "Evening Hunter",
            originalTitle = "Evening Hunter",
            releaseDate = "2026-01-05",
            overview = "The Hunter's pursuit intensifies as night falls."
        ),
        sampleMovie.copy(
            id = 550996L,
            title = "Sunrise Hunter",
            originalTitle = "Sunrise Hunter",
            releaseDate = "2026-06-20",
            overview = "The Hunter's story begins anew with the rising sun."
        ),
        sampleMovie.copy(
            id = 550997L,
            title = "Sunset Hunter",
            originalTitle = "Sunset Hunter",
            releaseDate = "2027-01-30",
            overview = "The Hunter's journey comes to a dramatic close at sunset."
        ),
        sampleMovie.copy(
            id = 550998L,
            title = "Eternal Hunter",
            originalTitle = "Eternal Hunter",
            releaseDate = "2028-04-18",
            overview = "The Hunter's legend lives on for eternity."
        )
    )

    fun createMovieFlow(movies: List<MovieDomainModel>): Flow<List<MovieDomainModel>> {
        return flowOf(movies)
    }
}

enum class Route(val route: String) {
    List("list"), Details("details/{url}")
}*/
