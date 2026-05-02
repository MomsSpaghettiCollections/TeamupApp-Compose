package com.ups.android.apps.designsystem.components.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.ups.android.apps.designsystem.theme.graphExpense
import com.ups.android.apps.designsystem.theme.graphIncome

enum class TextType {
    HEADLINE_MEDIUM, HEADLINE_LARGE,
    TITLE_MEDIUM, TITLE_LARGE,
    BODY_MEDIUM, BODY_LARGE,
    DISPLAY_MEDIUM_INCOME, DISPLAY_MEDIUM_EXPENSE,
    DISPLAY_SMALL_INCOME, DISPLAY_SMALL_EXPENSE,
    BUTTON_LABEL, BUTTON_INACTIVE,
    LABEL_SMALL
}

@Composable
fun TextType.getColor() = when (this) {
    TextType.HEADLINE_MEDIUM,
    TextType.HEADLINE_LARGE -> MaterialTheme.colorScheme.onSurface

    TextType.TITLE_MEDIUM,
    TextType.TITLE_LARGE -> MaterialTheme.colorScheme.onSurface

    TextType.BODY_MEDIUM,
    TextType.BODY_LARGE -> MaterialTheme.colorScheme.onSurfaceVariant

    TextType.LABEL_SMALL -> MaterialTheme.colorScheme.onSurfaceVariant

    TextType.DISPLAY_MEDIUM_INCOME,
    TextType.DISPLAY_SMALL_INCOME -> graphIncome

    TextType.DISPLAY_MEDIUM_EXPENSE,
    TextType.DISPLAY_SMALL_EXPENSE -> graphExpense

    TextType.BUTTON_LABEL -> MaterialTheme.colorScheme.onSurfaceVariant
    TextType.BUTTON_INACTIVE -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
}


@Composable
fun TextType.getStyle() = when (this) {

    TextType.TITLE_MEDIUM -> MaterialTheme.typography.titleMedium

    TextType.TITLE_LARGE -> MaterialTheme.typography.titleLarge

    TextType.BODY_MEDIUM -> MaterialTheme.typography.bodyMedium

    TextType.BODY_LARGE -> MaterialTheme.typography.bodyLarge

    TextType.LABEL_SMALL-> MaterialTheme.typography.labelSmall

    TextType.DISPLAY_MEDIUM_INCOME,
    TextType.DISPLAY_MEDIUM_EXPENSE -> MaterialTheme.typography.displayMedium

    TextType.DISPLAY_SMALL_INCOME,
    TextType.DISPLAY_SMALL_EXPENSE -> MaterialTheme.typography.displaySmall

    TextType.HEADLINE_MEDIUM -> MaterialTheme.typography.headlineMedium
    TextType.HEADLINE_LARGE -> MaterialTheme.typography.headlineLarge

    TextType.BUTTON_LABEL,
    TextType.BUTTON_INACTIVE -> MaterialTheme.typography.labelLarge
}