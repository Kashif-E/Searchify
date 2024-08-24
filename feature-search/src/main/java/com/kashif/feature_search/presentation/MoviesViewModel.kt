package com.kashif.feature_search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shared.domain.models.MovieDomainModel
import com.kashif.domain.di.DefaultDispatcher
import com.kashif.domain.di.IoDispatcher

import com.kashif.feature_search.data.repository.IMovieRepository
import com.kashif.feature_search.domain.Result
import com.kashif.feature_search.domain.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieRepository: IMovieRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {


    private var currentPage = 1
    private val _moviePagingState = MutableStateFlow<PagingState>(PagingState.Idle)
    val moviePagingState = _moviePagingState.asStateFlow()
    private val _movies = MutableStateFlow(listOf<MovieDomainModel>())
    val moviesState = _movies.asStateFlow()
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _searchMoviesState by lazy {
        searchQuery.debounce(300L).filter { query ->

            query.isNotEmpty()
        }.map { query ->
            val queryCharacters = query.toCharArray().filter { it.isWhitespace().not() }
            moviesState.value.filter { data ->

                queryCharacters.all { char ->
                    data.title.contains(char, true)
                }
            }.sortedBy { queryCharacters[0] }

        }.stateIn(viewModelScope + defaultDispatcher, SharingStarted.Eagerly, emptyList())
    }
    val searchMoviesState = _searchMoviesState



    fun getCurrentPage() = currentPage
    fun onEvent(event: UIEvent) {
        when (event) {
            UIEvent.FetchNextPage -> fetchNextPage()
            is UIEvent.UpdateSearchQuery -> updateSearch(event.query)
            UIEvent.Retry -> fetchNextPage()
        }
    }

    init {
        onEvent(UIEvent.FetchNextPage)
    }
    private fun fetchNextPage() {
        if (_moviePagingState.value !is PagingState.Loading && _moviePagingState.value !is PagingState.Appending) {
            getMovies()
        }

    }

    private fun getMovies() {
        viewModelScope.launch(ioDispatcher) {

            movieRepository.getMovies(currentPage).asResult().collectLatest { result ->
                when (result) {
                    is Result.Error -> {
                        _moviePagingState.update { PagingState.Error(result.errorMessage) }

                    }

                    Result.Loading -> {
                        _moviePagingState.update {
                            if (currentPage > 1) {
                                PagingState.Appending
                            } else {
                                PagingState.Loading
                            }
                        }

                    }

                    is Result.Success -> {
                        currentPage++
                        _moviePagingState.update { PagingState.Success }
                        _movies.update { movies ->
                            movies + result.data
                        }
                    }
                }
            }
        }
    }

    private fun updateSearch(searchText: String) {
        _searchQuery.update { searchText }
    }
}

sealed interface PagingState {
    data object Idle : PagingState
    data object Loading : PagingState

    data object Success : PagingState

    data object Appending : PagingState

    data class Error(val message: String) : PagingState

}

sealed interface SearchScreenState {
    data object NormalState : SearchScreenState
    data object SearchState : SearchScreenState
}

sealed interface UIEvent {
    data object FetchNextPage : UIEvent
    data object Retry : UIEvent
    data class UpdateSearchQuery(val query: String) : UIEvent
}


