package com.ups.android.apps.designsystem.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.ups.android.apps.designsystem.UiText


@Composable
fun ScreenContainer(
    showBanner: Boolean = false,
    bannerMessage: UiText,
    isErrorBanner: Boolean = true,
    content: @Composable () -> Unit
) {

    var showBannerMessage by remember { mutableStateOf(showBanner) }

    LaunchedEffect(key1 = showBanner) {
        showBannerMessage = showBanner
    }

    Surface(content = {
        Box(
            modifier = Modifier.fillMaxSize(),
            content = {
                content()

                AnimatedVisibility(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = if (isErrorBanner) Color.Red
                            else MaterialTheme.colorScheme.primary
                        ),
                    visible = showBannerMessage,
                    enter = slideInVertically(
                        initialOffsetY = { -30 }
                    ) + expandVertically(
                        expandFrom = Alignment.Top
                    ) + fadeIn(initialAlpha = 0.3f),
                    content = {
                        Text(
                            text = bannerMessage.getString(LocalContext.current)
                        )
                    }
                )
            })
    })
}