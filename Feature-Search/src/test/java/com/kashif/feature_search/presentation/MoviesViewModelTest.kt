package com.kashif.feature_search.presentation

import app.cash.turbine.test
import com.kashif.domain.models.MovieDomainModel
import com.kashif.feature_search.data.repository.IMovieRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class MoviesViewModelTest {


    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var movieRepository: IMovieRepository

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        movieRepository = mockk()
    }


    @Test
    fun `paging exception are handled gracefully without affecting moviesList`() = runTest {
        // Act
        coEvery { movieRepository.getMovies(1) } returns flow {
            emit(VMTestUtils.sampleMovies)
            throw Exception("Network Error")
        }
        val viewModel = MoviesViewModel(movieRepository, testDispatcher, testDispatcher)
        // Verify
        coVerify {
            movieRepository.getMovies(1)
        }

        // Assert
        viewModel.moviePagingState.test {
            assert(awaitItem() is PagingState.Error)
            assert(viewModel.moviesState.value == VMTestUtils.sampleMovies)
        }
    }

    @Test
    fun `verify paging works correctly`() = runTest {
        // Act
        coEvery { movieRepository.getMovies(1) } returns flow {
            emit(VMTestUtils.sampleMovies)

        }
        coEvery { movieRepository.getMovies(2) } returns flow {
            emit(listOf(VMTestUtils.sampleMovie.copy(page = 2)))

        }
        val viewModel = MoviesViewModel(movieRepository, testDispatcher, testDispatcher)

        viewModel.onEvent(UIEvent.FetchNextPage)
        // Verify
        coVerify {
            movieRepository.getMovies(1)
        }
        coVerify {
            movieRepository.getMovies(2)
        }

        // Assert
        viewModel.moviesState.test {
            val item = awaitItem()
            assert(item == VMTestUtils.sampleMovies + VMTestUtils.sampleMovie.copy(page = 2))
        }
    }

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


