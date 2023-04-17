package com.rezyfr.aej.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

val ColorScheme = darkColorScheme(
    primary = Color(0xFF2D9CDB),
    inversePrimary = ColorSpectrum.brand_02,
    secondary = Color(0xFFF48FB1),
    primaryContainer = ColorSpectrum.grey_09_5,
    secondaryContainer = ColorSpectrum.grey_09,
    tertiaryContainer = ColorSpectrum.grey_08,
    onPrimaryContainer = ColorSpectrum.grey_00,
    onSecondaryContainer = ColorSpectrum.grey_01,
    onSurface = ColorSpectrum.grey_03,
)
@Composable
fun AejTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit
) {

    CompositionLocalProvider {
        MaterialTheme(
            typography = MainTypography, content = content, colorScheme = ColorScheme
        )
    }
}