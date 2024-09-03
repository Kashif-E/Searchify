package com.kashif.feature_search.presentation

import android.util.Log
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.shared.domain.models.MovieDomainModel
import com.example.shared.presentation.MoviesViewModel
import com.example.shared.presentation.PagingState
import com.example.shared.presentation.SearchScreenState
import com.example.shared.presentation.UIEvent
import com.kashif.designsystem.components.AppBar
import com.kashif.designsystem.theme.colors.LocalColors
import com.kashif.designsystem.theme.spacing.LocalSpacing
import com.kashif.feature_search.R
import com.kashif.feature_search.presentation.components.MovieCard
import com.kashif.feature_search.presentation.components.SearchCardWithBorder
import com.kashif.feature_search.presentation.components.paging
import kotlinx.coroutines.launch
import kotlin.reflect.KFunction1

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.SearchScreen(
    viewModel: MoviesViewModel,
    onItemClick: (url: String) -> Unit,
    animatedVisibilityScope: AnimatedContentScope
) {
    val pagingState by viewModel.moviePagingState.collectAsState()
    val moviesState by viewModel.moviesState.collectAsState()
    val searchMoviesState by viewModel.searchMoviesState.collectAsState()
    val searchText by viewModel.searchQuery.collectAsState()


    val screenState: SearchScreenState by remember {
        derivedStateOf {
            if (searchText.isEmpty()) {
                SearchScreenState.NormalState
            } else {
                SearchScreenState.SearchState
            }
        }
    }


    Body(
        searchText,
        viewModel::onEvent,
        screenState,
        moviesState,
        searchMoviesState,
        viewModel.getCurrentPage(),
        pagingState,
        animatedVisibilityScope,
        onItemClick
    )

}

@OptIn(ExperimentalMaterialApi::class, ExperimentalSharedTransitionApi::class)
@Composable
private fun SharedTransitionScope.Body(
    searchText: String,
    onEvent: KFunction1<UIEvent, Unit>,
    screenState: SearchScreenState,
    moviesState: List<MovieDomainModel>,
    searchMoviesState: List<MovieDomainModel>,
    moviePagingState: Int,
    pagingState: PagingState,
    animatedVisibilityScope: AnimatedContentScope,
    onItemClick: (url: String) -> Unit
) {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var isRefreshing by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            scope.launch {
                onEvent(UIEvent.FetchNextPage)
                isRefreshing = false
            }
        }
    )
    Scaffold(
        modifier = Modifier
            .windowInsetsPadding(
                WindowInsets.systemBars
            )
            .fillMaxSize(), topBar = {
            AppBar(title = stringResource(R.string.searchify))
        }, scaffoldState = scaffoldState
    ) { paddingValues ->
        Box(modifier = Modifier.pullRefresh(state = pullRefreshState)) {
            ScreenContent(
                paddingValues,
                searchText,
                onEvent,
                screenState,
                moviesState,
                moviePagingState,
                searchMoviesState,
                animatedVisibilityScope,
                onItemClick,

                )
            PaginationIndicator(pagingState, scaffoldState, onEvent = onEvent)


            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }

    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun SharedTransitionScope.ScreenContent(
    paddingValues: PaddingValues,
    searchText: String,
    onEvent: KFunction1<UIEvent, Unit>,
    screenState: SearchScreenState,
    moviesState: List<MovieDomainModel>,
    currentPage: Int,
    searchMoviesState: List<MovieDomainModel>,
    animatedVisibilityScope: AnimatedContentScope,
    onItemClick: (url: String) -> Unit,
) {

    val lazyListState = rememberLazyStaggeredGridState()
    val localFocusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = LocalSpacing.current.m)
            .padding(top = LocalSpacing.current.m)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            LocalSpacing.current.l
        )
    ) {
        SearchCardWithBorder(
            valueText = searchText,
            label = stringResource(R.string.search_movies),
            onTextChange = { query ->
                if (query.isEmpty()) {
                    localFocusManager.clearFocus(true)
                }
                onEvent(UIEvent.UpdateSearchQuery(query))
            },
            onSubmit = {
                localFocusManager.clearFocus()
                onEvent(UIEvent.UpdateSearchQuery(searchText))
            })


        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(110.dp),
            state = lazyListState,
            modifier = Modifier
                .background(LocalColors.current.background)
                .systemBarsPadding(),
            horizontalArrangement = Arrangement.spacedBy(LocalSpacing.current.l),
            verticalItemSpacing = LocalSpacing.current.s,
        ) {
            when (screenState) {
                SearchScreenState.NormalState -> {
                    paging(
                        items = moviesState,
                        currentPage = currentPage,
                        fetch = { onEvent(UIEvent.FetchNextPage) }) { movie, index ->
                        MovieCard(
                            modifier = Modifier.sharedElement(
                                state = rememberSharedContentState(
                                    key = "image-${movie.posterPath}"
                                ),
                                animatedVisibilityScope = animatedVisibilityScope,
                            ), movie = movie, onClick = onItemClick
                        )
                    }
                }

                SearchScreenState.SearchState -> {
                    itemsIndexed(searchMoviesState, contentType = { movie, index ->
                        movie::class.java.name
                    }) { index, movie ->
                        MovieCard(
                            modifier = Modifier.sharedElement(
                                state = rememberSharedContentState(
                                    key = "image-${movie.posterPath}"
                                ),
                                animatedVisibilityScope = animatedVisibilityScope,
                            ), movie = movie, onClick = onItemClick
                        )
                    }
                }

            }

        }

    }

}

@Composable
fun BoxScope.PaginationIndicator(
    pagingState: PagingState,
    scaffoldState: ScaffoldState,
    retryLabel: String = stringResource(id = R.string.retry),
    onEvent: KFunction1<UIEvent, Unit>
) {
    when (pagingState) {
        PagingState.Appending -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.BottomCenter),
                strokeCap = StrokeCap.Round,
                backgroundColor = LocalColors.current.greyLight,
                strokeWidth = 10.dp
            )
        }

        is PagingState.Error -> {

            LaunchedEffect(key1 = pagingState) {

                val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                    pagingState.message, actionLabel = retryLabel
                )
                when (snackbarResult) {
                    SnackbarResult.Dismissed -> Log.d("snackbar", "dismissed")
                    SnackbarResult.ActionPerformed -> onEvent(UIEvent.Retry)
                }

            }

        }

        PagingState.Idle -> {
            //do nothing
        }

        PagingState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.Center),
                strokeCap = StrokeCap.Round,
                backgroundColor = LocalColors.current.greyLight,
                strokeWidth = 10.dp
            )
        }

        PagingState.Success -> {
            //do nothing
        }
    }
}


