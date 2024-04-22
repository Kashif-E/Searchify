package com.kashif.feature_search.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.kashif.designsystem.theme.colors.LocalColors
import com.kashif.designsystem.theme.shapes.LocalShapes
import com.kashif.designsystem.theme.spacing.LocalSpacing
import com.kashif.domain.models.MovieDomainModel
import androidx.compose.animation.ExperimentalSharedTransitionApi

@Composable
fun MovieCard(
    movie: MovieDomainModel,
    onClick: (url: String) -> Unit,
    modifier: Modifier
) {
    val click = remember {
        Modifier.clickable {
            onClick(movie.posterPath!!)
        }
    }
    Card(
        elevation = 1.dp,
        backgroundColor = LocalColors.current.surface,
        shape = LocalShapes.current.cardShape,
        border = BorderStroke(1.dp, LocalColors.current.grey),
        modifier = Modifier.then(click)
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = LocalSpacing.current.s)
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(LocalSpacing.current.s)
        ) {
            SubcomposeAsyncImage(
                modifier = modifier
                    .height(160.dp)
                    .fillMaxWidth()
                    .clip(LocalShapes.current.imageShape),
                model = ImageRequest.Builder(LocalContext.current).data(movie.posterPath)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.FillBounds,
                contentDescription = movie.originalTitle
            )

            Text(
                text = movie.originalTitle,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LocalSpacing.current.s),
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        }
    }


}