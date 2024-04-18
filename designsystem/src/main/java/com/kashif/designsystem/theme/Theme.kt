package com.kashif.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.kashif.designsystem.theme.colors.LocalColors
import com.kashif.designsystem.theme.colors.darkMovieColors
import com.kashif.designsystem.theme.colors.lightMovieColors
import com.kashif.designsystem.theme.shapes.LocalShapes
import com.kashif.designsystem.theme.shapes.Shapes
import com.kashif.designsystem.theme.spacing.ExtendedSpacing
import com.kashif.designsystem.theme.spacing.LocalSpacing
import com.kashif.designsystem.theme.typography.Type

/**
 * Used to auto apply basic colors
 */
@Composable
fun getColorPalette(darkTheme: Boolean): Colors =  Colors(
    primary = LocalColors.current.primary,
    onPrimary = LocalColors.current.onPrimary,
    surface = LocalColors.current.surface,
    onSurface = LocalColors.current.onSurface,
    background = LocalColors.current.background,
    onBackground = LocalColors.current.onBackground,
    secondary = LocalColors.current.secondary,
    onSecondary = LocalColors.current.onSecondary,
    secondaryVariant = LocalColors.current.secondaryVariant,
    error = LocalColors.current.error,
    onError = LocalColors.current.onError,
    primaryVariant = LocalColors.current.primaryVariant,
    isLight = !darkTheme
)

/**
     * Searchify custom theme for the entire Compose hierarchy.
     * @param darkTheme Whether to apply the dark theme (true) or the light theme (false). Default value is determined by the system's dark theme setting.
     * @param content The content that should be displayed within the theme.
     */
    @Composable
    fun SearchifyTheme(
        darkTheme: Boolean = isSystemInDarkTheme(),
        content: @Composable () -> Unit
    ) {

        // CompositionLocalProvider is used to provide custom theme values to the Compose hierarchy.
        CompositionLocalProvider(
            // Provide the extended colors based on the darkTheme parameter.
            LocalColors provides if (darkTheme) darkMovieColors else lightMovieColors,
            // Provide the custom shapes defined by Shapes.
            LocalShapes provides Shapes,
            // Provide the custom spacing values.
            LocalSpacing provides ExtendedSpacing(),
        ) {
            // Apply the MaterialTheme with the custom typography and the content provided in the composable.
            MaterialTheme(
                typography = Type.typography, content = content, colors = getColorPalette(darkTheme)
            )
        }
    }

