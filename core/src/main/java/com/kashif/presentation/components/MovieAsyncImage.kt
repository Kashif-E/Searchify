package com.kashif.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest

@Composable
fun MovieAsyncImage(
    modifier: Modifier = Modifier,
    url: String,
    contentScale: ContentScale = ContentScale.FillBounds,
    description: String
) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current).data(url)
            .crossfade(true)
            .build(),
        contentScale = contentScale,
        contentDescription = description
    )
}
