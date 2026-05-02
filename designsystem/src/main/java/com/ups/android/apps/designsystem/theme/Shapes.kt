package com.ups.android.apps.designsystem.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

data class Shapes(
    val button: Shape = RoundedCornerShape(8.dp),
    val dots: Shape = CircleShape,
    val pagerIndicator: Shape = RoundedCornerShape(percent = 50),
    val bar: Shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp)
)

val LocalShapes = compositionLocalOf { Shapes() }