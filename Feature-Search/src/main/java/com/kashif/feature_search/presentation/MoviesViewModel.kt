package com.kashif.feature_search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kashif.domain.di.DefaultDispatcher
import com.kashif.domain.di.IoDispatcher
import com.kashif.domain.models.MovieDomainModel
import com.kashif.feature_search.data.repository.IMovieRepository
import com.kashif.feature_search.domain.Result
import com.kashif.feature_search.domain.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
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
    val searchQuery = MutableStateFlow("")

    private val _searchMoviesState = MutableStateFlow<List<MovieDomainModel>>(listOf())
    val searchMoviesState = _searchMoviesState.asStateFlow()

    init {
        viewModelScope.launch(defaultDispatcher) {
            searchQuery.debounce(300L).filter { query ->
                query.isNotEmpty()
            }.map { query ->
                val queryCharacters = query.toCharArray().filter { it.isWhitespace().not() }
                moviesState.value.filter { data ->
                    queryCharacters.all { char ->
                        data.title.contains(char, true)
                    }
                }.sortedBy { queryCharacters[0] }

            }.collectLatest { movielist ->
                _searchMoviesState.update { movielist }
            }
        }
        onEvent(UIEvent.FetchNextPage)
    }

    fun getCurrentPage() = currentPage
    fun onEvent(event: UIEvent) {
        when (event) {
            UIEvent.FetchNextPage -> fetchNextPage()
            is UIEvent.UpdateSearchQuery -> updateSearch(event.query)
            UIEvent.Retry -> fetchNextPage()
        }
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
                        delay(1000)
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
        searchQuery.update { searchText }
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


