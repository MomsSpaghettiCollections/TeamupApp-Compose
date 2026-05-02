package com.ups.android.apps.designsystem.components.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ups.android.apps.designsystem.components.text.TextType
import com.ups.android.apps.designsystem.theme.LocalDimensions


enum class ButtonType {
    FILLED_TONAL,
    TEXT,
    ELEVATED
}

fun ButtonType.getTextType() = when (this) {
    ButtonType.FILLED_TONAL -> TextType.TITLE_MEDIUM
    ButtonType.TEXT -> TextType.BODY_MEDIUM
    ButtonType.ELEVATED -> TextType.TITLE_LARGE
}

@Composable
fun ButtonType.getButtonElevation() = when (this) {
    ButtonType.FILLED_TONAL -> ButtonDefaults.filledTonalButtonElevation()
    ButtonType.TEXT -> ButtonDefaults.buttonElevation()
    ButtonType.ELEVATED -> ButtonDefaults.elevatedButtonElevation()
}

@Composable
fun ButtonType.getButtonColor() = when (this) {
    ButtonType.FILLED_TONAL -> ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    )

    ButtonType.TEXT -> ButtonDefaults.textButtonColors()
    ButtonType.ELEVATED -> ButtonDefaults.elevatedButtonColors()
}

@Composable
fun Modifier.buttonWidth(isFullWidth: Boolean) = if (isFullWidth) {
    this@buttonWidth.fillMaxWidth()
} else {
    this@buttonWidth.width(LocalDimensions.current.dimensions180)
}.height(LocalDimensions.current.dimensions56)