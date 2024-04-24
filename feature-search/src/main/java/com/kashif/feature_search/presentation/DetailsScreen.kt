package com.kashif.feature_search.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kashif.designsystem.components.AppBar
import com.kashif.designsystem.theme.colors.LocalColors
import com.kashif.designsystem.theme.spacing.LocalSpacing
import com.kashif.feature_search.R

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailsScreen(
    url: String,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    moviesViewModel: MoviesViewModel
) {

    val movieState by moviesViewModel.moviesState.collectAsState()
    val currentMovie by remember {
        derivedStateOf {
            movieState.first { it.posterPath == url }
        }
    }
    Scaffold(topBar = {
       AppBar(title = currentMovie.title, onNavigation = onClick)
    }, contentWindowInsets = WindowInsets.systemBars) { paddingValues ->

        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(LocalSpacing.current.l)
        ) {
            AsyncImage(
                model = currentMovie.posterPath,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .sharedElement(
                        rememberSharedContentState(key = "image-$url"),
                        animatedVisibilityScope,
                    ),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Column(
                horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.spacedBy(
                    LocalSpacing.current.m
                ),
                modifier = Modifier.padding(horizontal = LocalSpacing.current.l)
            ) {
                Text(text = "Title ${currentMovie.title}", style = MaterialTheme.typography.h4)
                Text(text = "Release Date ${currentMovie.releaseDate}")
                Text(text = "Overview ${currentMovie.overview}")
                Text(text = "Vote average: ${currentMovie.title}")

            }

        }
    }

}