package com.kashif.designsystem.theme.colors

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


val Green10 = Color(0xFFE3F2FD)
private val Green20 = Color(0xFFBBDEFB)
private val Green30 = Color(0xFF90CAF9)
private val Green40 = Color(0xFF64B5F6)
private val Green50 = Color(0xFF42A5F5)
private val Green70 = Color(0xFF1976D2)
private val Green80 = Color(0xFF0D47A1)



private val Red20 = Color(0xFFFFDCDC)
private val Red30 = Color(0xFFFFB5B5)
private val Red40 = Color(0xFFE57373)
private val Red50 = Color(0xFFEA5B5B)
private val Red70 = Color(0xFFFF002A)

private val Yellow20 = Color(0xFFFFE2C7)
private val Yellow30 = Color(0xFFFFD9B8)
private val Yellow50 = Color(0xFFFFBE21)
private val Yellow60 = Color(0xFFEDB120)

private val Black = Color(0xFF000000)

private val Grey10 = Color(0xFFE6E6E6)
private val Grey20 = Color(0xFFD9D9D9)
private val Grey30 = Color(0xFFBEBEBE)
private val Grey40 = Color(0xFFA6A6A6)
private val Grey50 = Color(0xFF7F7F7F)
private val Grey60 = Color(0xFF595959)
private val Grey70 = Color(0xFF262626)
private val Grey90 = Color(0xFF000000)
private val Grey100 = Color(0xFF000000)
val Grey110 = Color(0xFF000000)
val fadeWhite = Color(0x99CCCCCC)




private val YellowGreenSecondary = Color(0xFF6EF633)




/**
 * Custom color palette for the light theme.
 */
val lightExtendedColors =
    ExtendedColors(
        primary = Green50,
        primaryVariant = Grey50,
        secondary = Grey50,
        secondaryVariant = YellowGreenSecondary,
        background = Grey20,
        surface = Grey10,
        onPrimary = Color.White,
        onSecondary = Grey10,
        onBackground = Grey50,
        onSurface = Color.Black,
        error = Red50,
        onError = Grey10,
        primaryVariantLight = Grey20,
        primaryVariantLightBG = Grey20,
        errorLight = Red20,
        primaryLight = Green10,
        primaryLightBG = Green10,
        greyLight = Grey30,
        grey = Grey40,
        greyMid = Grey50,
        greyDark = Grey60,
        fieldPlaceHolderText = Grey60,
        greyBg = Grey40,
        greyDarkText = Grey60,
        warningLight = Yellow20,
        warning = Yellow50,
        primaryDark = Green70,
        youtube = Red70,
        secondarySurface = Grey60,
        productViewBG = Grey110,
        toastBackground = Grey110,
        toastText = Grey90,
        neutralBlue = Grey50,
        neutralWhite = Grey10.copy(alpha = 0.6f),
        primaryLightBG2 = Green30,
        colorWhiteTransparent = fadeWhite,
        lightGreenBG1 = Green20
    )

/**
 * Custom color palette for the dark theme.
 */


val darkExtendedColors =
    ExtendedColors(
        primaryVariantLight = Grey30,
        primaryVariantLightBG = Grey20,
        errorLight = Red20,
        primaryLight = Green20,
        primaryLightBG = Grey90,
        greyLight = Grey90,
        grey = Grey70,
        greyMid = Grey60,
        greyDark = Grey60,
        fieldPlaceHolderText = Grey60,
        greyBg = Grey50,
        greyDarkText = Grey70,
        warning = Yellow60,
        warningLight = Yellow30,
        primaryDark = Green80,
        youtube = Red70,
        secondarySurface = Grey100,
        productViewBG = Grey110,
        primary = Green70,
        primaryVariant = Grey60,
        secondary = Grey60,
        secondaryVariant = YellowGreenSecondary,
        background = Grey110,
        surface = Grey100,
        onPrimary = Color.White,
        onSecondary = Grey40,
        onBackground = Grey40,
        onSurface = Color.White,
        error = Red40,
        onError = Grey40,
        toastBackground = Grey20,
        toastText = Grey30,
        neutralBlue = Grey50,
        neutralWhite = Grey10.copy(alpha = 0.6f),
        primaryLightBG2 = Green10,
        colorWhiteTransparent = fadeWhite,
        lightGreenBG1 = Green40
    )

/**
 * A [CompositionLocal] that provides the light theme color palette.
 */

val LocalColors = staticCompositionLocalOf { lightExtendedColors }

