package com.ups.android.apps.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalConfiguration


sealed interface WindowType {
    object Compact : WindowType
    object Medium : WindowType
    object Expanded : WindowType
}

@Composable
fun rememberWindowType(): WindowType {
    val configuration = LocalConfiguration.current
    return when {
        configuration.screenWidthDp < 600 -> WindowType.Compact
        configuration.screenWidthDp < 840 -> WindowType.Medium
        else -> WindowType.Expanded
    }
}

val LocalWindowType = compositionLocalOf<WindowType> { error("WindowType not provided") }