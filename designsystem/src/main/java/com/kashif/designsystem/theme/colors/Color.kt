package com.kashif.designsystem.theme.colors

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Searchify material theme color palette.
 * Provides custom color variants that are not in the Material Theme.
 */

val Green10 = Color(0XFFE3F4E7)
private val Green20 = Color(0xFFC7E5CE)
private val Green30 = Color(0xFFb5cebb)
private val Green40 = Color(0xFFb5cebb)
private val Green50 = Color(0xFF02BC87)
private val Green70 = Color(0xFF009578)
private val Green80 = Color(0xFF047861)
val DeepBlue = Color(0xFF0D253F)
val RichGold = Color(0xFFF9A825)
val VelvetRed = Color(0xFFD32F2F)
val StarlightWhite = Color(0xFFFAFAFA)  // A slightly off-white color
val MidnightBlack = Color(0xFF121212)
val CinematicTeal = Color(0xFF00838F)

private val Red20 = Color(0xFFFFDCDC)
private val Red30 = Color(0xFFDBB5B5)
private val Red40 = Color(0xFFE57373)
private val Red50 = Color(0xFFEA5B5B)
private val Red70 = Color(0xFFFF002A)

private val Yellow20 = Color(0xFFFFE2C7)
private val Yellow30 = Color(0xFFD9B899)
private val Yellow50 = Color(0xFFFFBE21)
private val Yellow60 = Color(0xFFEDB120)

private val Blue20 = Color(0xFFD7F6FF)
private val Blue30 = Color(0xFFa9d7e4)
private val Blue50 = Color(0xFF009DCD)
private val Blue60 = Color(0xFF008bb5)
val DarkBlue50 = Color(0xFF070A56)
private val DarkBlue60 = Color(0xFF43468b)

private val YellowGreenSecondary = Color(0xFF6EF633)

private val Grey10 = Color(0xffffffff)
private val Grey20 = Color(0xfffcfcfc)
private val Grey30 = Color(0xfff3f4f5)
private val Grey40 = Color(0xffe5e5e5)
private val Grey50 = Color(0xffb5b4ba)
private val Grey60 = Color(0xff84858d)
private val Grey70 = Color(0xff3d3d3e)
private val Grey90 = Color(0xff1f1f1f)
private val Grey100 = Color(0xff161616)
val Grey110 = Color(0xff0f0f0f)
val fadeWhite = Color(0x99FFFFFF)

private val WhiteTransparent = Color(0x44FFFFFF)
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
    warningLight = Color(0xFFFFE082),  // Lighter gold
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
 * A [CompositionLocal] that provides the light theme color palette.
 */

val LocalColors = staticCompositionLocalOf { lightMovieColors }

