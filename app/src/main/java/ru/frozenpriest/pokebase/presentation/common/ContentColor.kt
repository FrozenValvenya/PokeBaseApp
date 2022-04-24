package ru.frozenpriest.pokebase.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import timber.log.Timber

/**
 * Works only for rgb, does not react to alpha
 */
@Composable
@ReadOnlyComposable
fun blackOrWhiteContentColor(background: Color): Color {
    val colorSpaceCombined = with(background.colorSpace) {
        getMaxValue(0) + getMaxValue(1) + getMaxValue(2)
    }
    val contrastRatio = (background.red + background.green + background.blue) / (3f * colorSpaceCombined)
    Timber.i("Contrast ratio is $contrastRatio")
    return if (contrastRatio > 0.2f) Color.Black else Color.White
}
