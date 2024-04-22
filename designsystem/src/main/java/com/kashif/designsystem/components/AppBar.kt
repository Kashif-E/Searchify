package com.kashif.designsystem.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import com.kashif.designsystem.theme.colors.LocalColors

@Composable
fun AppBar(title: String, onNavigation: (() -> Unit)? = null) {
    TopAppBar(
        backgroundColor = LocalColors.current.surface, title = {
            Text(text = title, style = MaterialTheme.typography.h4)
        }, navigationIcon = if (onNavigation != null) {
            {
                IconButton(onClick = onNavigation) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = LocalColors.current.onSurface
                    )
                }
            }
        } else null
    )

}