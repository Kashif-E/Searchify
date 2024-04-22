package com.kashif.designsystem.theme.shapes

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/**
 * Data class representing the extended shapes used in the application.
 *
 * @param cardShape The custom shape for cards.
 * @param bottomSheetShape The custom shape for bottom sheets.
 */
@Immutable
data class ExtendedShapes(
    val cardShape: Shape,
    val buttonShape: Shape,
    val chipShape: Shape,
    val textFieldShape: Shape,
    val variantShape: Shape,
    val imageShape: Shape,
    val pillShape: Shape,
    val statusShape: Shape
)

/**
 * An instance of the custom [ExtendedShapes] class with specific rounded corner shapes for cards
 * and bottom sheets.
 */
val Shapes =
    ExtendedShapes(
        cardShape = RoundedCornerShape(12.dp),
        buttonShape = RoundedCornerShape(8.dp),
        chipShape = RoundedCornerShape(14.dp),
        textFieldShape = RoundedCornerShape(8.dp),
        variantShape = RoundedCornerShape(8.dp),
        imageShape = RoundedCornerShape(
            topStart = 8.dp,
            topEnd = 8.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp
        ),
        pillShape = RoundedCornerShape(30.dp),
        statusShape = RoundedCornerShape(6.dp)
    )

/**
 * A [CompositionLocal] to store the custom shapes, making them accessible throughout the Compose
 * hierarchy.
 */
val LocalShapes = compositionLocalOf { Shapes }
