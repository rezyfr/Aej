package com.rezyfr.aej.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ExtendedColors(
    val colorPrimary: Color,
    val colorPrimaryDark: Color,
    val colorAccent: Color,
    val colorBackground: Color,
    val colorBackgroundSecondary: Color,
    val colorBackgroundCool: Color,
    val textIconPrimary: Color,
    val textIconSecondary: Color,
    val textIconSubdued: Color,
)

val LocalExtendedColors = staticCompositionLocalOf {
    ExtendedColors(
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
    )
}