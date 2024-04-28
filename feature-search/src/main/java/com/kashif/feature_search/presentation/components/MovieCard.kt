package com.kashif.feature_search.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.kashif.designsystem.theme.colors.LocalColors
import com.kashif.designsystem.theme.shapes.LocalShapes
import com.kashif.domain.models.MovieDomainModel
import com.kashif.presentation.components.MovieAsyncImage
import kotlin.random.Random
import kotlin.random.nextInt

@Composable
fun MovieCard(
    movie: MovieDomainModel,
    modifier: Modifier,
    onClick: (url: String) -> Unit,
) {
    val click = remember {
        Modifier.clickable {
            onClick(movie.posterPath!!)
        }
    }
    val cardSize = remember {
        getRandomSizeInRange()
    }
    Card(
        elevation = 1.dp,
        backgroundColor = LocalColors.current.surface,
        shape = LocalShapes.current.cardShape,
        border = BorderStroke(1.dp, LocalColors.current.grey),
        modifier = Modifier.then(click)
    ) {
        MovieAsyncImage(
            url = movie.posterPath!!, description = movie.title, modifier = modifier
                .height(cardSize.dp)
                .fillMaxWidth()
                .clip(LocalShapes.current.imageShape)
        )
    }
}

fun getRandomSizeInRange(): Int {
    val range = 250..400
    return Random.nextInt(range)
}