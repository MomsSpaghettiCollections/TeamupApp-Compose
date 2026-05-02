package com.ups.android.apps.designsystem.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val none: Dp = 0.dp,
    val dimensions2: Dp = 2.dp,
    val dimensions4: Dp = 4.dp,
    val dimensions8: Dp = 8.dp,
    val dimensions12: Dp = 12.dp,
    val dimensions16: Dp = 16.dp,
    val dimensions24: Dp = 24.dp,
    val dimensions28: Dp = 28.dp,
    val dimensions32: Dp = 32.dp,
    val dimensions40: Dp = 40.dp,
    val dimensions48: Dp = 45.dp,
    val dimensions56: Dp = 56.dp,
    val dimensions64: Dp = 64.dp,
    val dimensions128: Dp = 128.dp,
    val dimensions142: Dp = 142.dp,
    val dimensions180: Dp = 180.dp,
    val dimensions220: Dp = 220.dp,
    val dimensions300: Dp = 300.dp
)

val LocalDimensions = compositionLocalOf { Dimensions() }