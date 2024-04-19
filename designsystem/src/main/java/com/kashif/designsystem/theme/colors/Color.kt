package com.kashif.designsystem.theme.colors

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Searchify material theme color palette.
 * Provides custom color variants that are not in the Material Theme.
 */

val DeepBlue = Color(0xFF0D253F)
val RichGold = Color(0xFFF9A825)
val VelvetRed = Color(0xFFD32F2F)
val StarlightWhite = Color(0xFFFAFAFA)
val MidnightBlack = Color(0xFF121212)
val CinematicTeal = Color(0xFF00838F)

/**
 * Custom color palette for the light theme.
 */
val lightMovieColors = ExtendedColors(
    primary = CinematicTeal,
    primaryVariant = DeepBlue,
    secondary = RichGold,
    secondaryVariant = VelvetRed,
    background = StarlightWhite,
    surface = StarlightWhite,
    onPrimary = StarlightWhite,
    onSecondary = MidnightBlack,
    onBackground = DeepBlue,
    onSurface = DeepBlue,
    error = VelvetRed,
    onError = StarlightWhite,
    primaryVariantLight = Color(0xFF4FB3BF),
    primaryVariantLightBG = Color(0xFF4FB3BF),
    errorLight = Color(0xFFF8BBD0),
    primaryLight = CinematicTeal,
    primaryLightBG = StarlightWhite,
    greyLight = Color(0xFFF0F0F0),
    grey = Color(0xFFC0C0C0),
    greyMid = Color(0xFF9E9E9E),
    greyDark = Color(0xFF757575),
    fieldPlaceHolderText = Color(0xFF757575),
    greyBg = Color(0xFFE0E0E0),
    greyDarkText = Color(0xFF424242),
    warningLight = Color(0xFFFFE082),
    warning = RichGold,
    primaryDark = DeepBlue,
    youtube = VelvetRed,
    secondarySurface = MidnightBlack,
    productViewBG = MidnightBlack,
    toastBackground = MidnightBlack,
    toastText = StarlightWhite,
    neutralBlue = DeepBlue,
    neutralWhite = StarlightWhite.copy(alpha = 0.6f),
    primaryLightBG2 = Color(0xFFE1F5FE),
    colorWhiteTransparent = Color(0x44FFFFFF),
    lightGreenBG1 = Color(0xFFC8E6C9)
)
/**
 * Custom color palette for the dark theme.
 */
val darkMovieColors = ExtendedColors(
    primaryVariantLight = Color(0xFF37474F),  // Dark slate blue
    primaryVariantLightBG = MidnightBlack,
    errorLight = Color(0xFFEF9A9A),
    primaryLight = MidnightBlack,
    primaryLightBG = MidnightBlack,
    greyLight = MidnightBlack,
    grey = MidnightBlack,
    greyMid = Color(0xFF424242),
    greyDark = Color(0xFF424242),
    fieldPlaceHolderText = StarlightWhite,
    greyBg = Color(0xFF303030),
    greyDarkText = StarlightWhite,
    warning = Color(0xFFFBC02D),
    warningLight = Color(0xFFFFD54F),
    primaryDark = DeepBlue,
    youtube = VelvetRed,
    secondarySurface = Color(0xFF212121),
    productViewBG = MidnightBlack,
    primary = MidnightBlack,
    primaryVariant = DeepBlue,
    secondary = VelvetRed,
    secondaryVariant = RichGold,
    background = MidnightBlack,
    surface = MidnightBlack,
    onPrimary = StarlightWhite,
    onSecondary = StarlightWhite,
    onBackground = StarlightWhite,
    onSurface = StarlightWhite,
    error = VelvetRed,
    onError = StarlightWhite,
    toastBackground = StarlightWhite,
    toastText = MidnightBlack,
    neutralBlue = DeepBlue,
    neutralWhite = StarlightWhite.copy(alpha = 0.6f),
    primaryLightBG2 = CinematicTeal,
    colorWhiteTransparent = Color(0x66FFFFFF),
    lightGreenBG1 = Color(0xFF455A64)
)


/**
 * A [LocalColors] is a CompositionLocal that provides the light theme color palette by default.
 */

val LocalColors = staticCompositionLocalOf { lightMovieColors }

