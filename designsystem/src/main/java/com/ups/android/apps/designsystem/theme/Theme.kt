package com.ups.android.apps.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import com.ups.android.apps.designsystem.theme.LocalWindowType
import com.ups.android.apps.designsystem.theme.rememberWindowType


private val LightColorScheme = lightColorScheme(
    primary = blue,
    surface = light,
    surfaceContainerHigh = gray,
    onSurface = Color.Black,
    onSurfaceVariant = Color.DarkGray
)

@Composable
fun TeamupAppComposeTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalDimensions provides Dimensions(),
        LocalShapes provides Shapes(),
        LocalWindowType provides rememberWindowType()
    ) {
        MaterialTheme(
            colorScheme = LightColorScheme,
            typography = Typography,
            content = content
        )
    }
}