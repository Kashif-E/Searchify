package com.kashif.feature_search.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.kashif.designsystem.components.AppBar
import com.kashif.designsystem.theme.colors.LocalColors
import com.kashif.designsystem.theme.shapes.LocalShapes
import com.kashif.designsystem.theme.spacing.LocalSpacing
import com.kashif.presentation.components.MovieAsyncImage

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
    val currentMovie = remember {
        movieState.first { it.posterPath == url }

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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            ) {
                MovieAsyncImage(
                    url = currentMovie.posterPath!!,
                    modifier = Modifier
                        .fillMaxSize()
                        .sharedElement(
                            rememberSharedContentState(key = "image-$url"),
                            animatedVisibilityScope,
                        ),
                    description = currentMovie.title,
                    contentScale = ContentScale.FillBounds
                )
                Pill(
                    text = currentMovie.voteAverage,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(
                            LocalSpacing.current.m
                        )
                )
            }

            Column(
                horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.spacedBy(
                    LocalSpacing.current.m
                ),
                modifier = Modifier.padding(horizontal = LocalSpacing.current.l)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Title ${currentMovie.title}",
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.width(200.dp)
                    )
                    Pill(text = "Release Date: ${currentMovie.releaseDate}")
                }
                Divider(modifier = Modifier.fillMaxWidth(), color = LocalColors.current.greyLight)
                Text(text = "Overview:", style = MaterialTheme.typography.h4)
                Text(
                    text = "Overview ${currentMovie.overview}",
                    style = MaterialTheme.typography.body2
                )


                Divider(modifier = Modifier.fillMaxWidth(), color = LocalColors.current.greyLight)

               /* Text(text = "Genre:", style = MaterialTheme.typography.h4)
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    currentMovie.genreIds.forEach {
                        Pill(text = it.toString())
                    }


                }

                Divider(modifier = Modifier.fillMaxWidth(), color = LocalColors.current.greyLight)*/
                MovieAsyncImage(
                    url = currentMovie.backdropPath!!,
                    description = currentMovie.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(LocalShapes.current.cardShape)
                )
                Divider(modifier = Modifier.fillMaxWidth(), color = LocalColors.current.greyLight)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Popularity",
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.width(200.dp)
                    )
                    Pill(text = currentMovie.popularity)
                }
                Divider(modifier = Modifier.fillMaxWidth(), color = LocalColors.current.greyLight)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Vote Count",
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.width(200.dp)
                    )
                    Pill(text = currentMovie.voteCount)
                }
                
                Spacer(modifier = Modifier.height(20.dp))
            }
            

        }
    }

}

@Composable
private fun Pill(modifier: Modifier = Modifier, text: String) {
    Box(
        modifier = modifier
            .clip(LocalShapes.current.pillShape)
            .background(LocalColors.current.primary)
            .padding(LocalSpacing.current.m)
            .wrapContentSize(), contentAlignment = Alignment.Center
    ) {
        Text(
            text = text, style = MaterialTheme.typography.h6.copy(
                LocalColors.current.onPrimary
            )
        )
    }
}