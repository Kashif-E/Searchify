package com.kashif.designsystem.theme.typography

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kashif.designsystem.R
import com.kashif.designsystem.theme.colors.LocalColors

val regular = FontFamily(Font(R.font.regular))
val semiBold = FontFamily(Font(R.font.light))
val bold = FontFamily(Font(R.font.bold))

/** Object that holds the custom typography for the Compose theme. */
object Type {
    /**
     * Provides the custom [Typography] for the Compose theme.
     *
     * The [Typography] object contains different text styles for various text elements in the theme.
     * Each text style is defined with a specific [FontFamily], [FontWeight], and fontSize [Int].
     *
     * @return The custom [Typography] for the theme.
     */
    val typography: Typography
        @Composable get() {
            return typography()
        }

    /**
     * Composable function that creates and returns the custom [Typography].
     *
     * @return The custom [Typography] instance.
     */
    @Composable
    private fun typography() = Typography(
        h1 = TextStyle(
            fontFamily = bold, fontSize = 32.sp, color = LocalColors.current.onSurface
        ), h2 = TextStyle(
            fontFamily = bold, fontSize = 24.sp, color = LocalColors.current.onSurface
        ), h3 = TextStyle(
            fontFamily = bold, fontSize = 20.sp, color = LocalColors.current.onSurface
        ), h4 = TextStyle(
            fontFamily = bold, fontSize = 16.sp, color = LocalColors.current.onSurface
        ), h5 = TextStyle(
            fontFamily = semiBold, fontSize = 14.sp, color = LocalColors.current.onSurface
        ), h6 = TextStyle(
            fontFamily = semiBold, fontSize = 12.sp, color = LocalColors.current.onSurface
        ), subtitle1 = TextStyle(
            fontFamily = semiBold, fontSize = 10.sp, color = LocalColors.current.onSurface
        ), subtitle2 = TextStyle(
            fontFamily = regular,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = LocalColors.current.onSurface
        ), body1 = TextStyle(
            fontFamily = regular,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = LocalColors.current.onSurface
        ), body2 = TextStyle(
            fontFamily = regular, fontSize = 12.sp, color = LocalColors.current.onSurface
        ), button = TextStyle(
            fontFamily = regular, fontSize = 14.sp, color = LocalColors.current.onPrimary
        ), caption = TextStyle(
            fontFamily = bold, fontSize = 12.sp, color = LocalColors.current.onSurface
        ), overline = TextStyle(
            fontFamily = bold, fontSize = 10.sp, color = LocalColors.current.onSurface
        )
    )
}


