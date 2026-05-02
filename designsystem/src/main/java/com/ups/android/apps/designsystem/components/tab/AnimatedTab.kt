package com.ups.android.apps.designsystem.components.tab

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ups.android.apps.designsystem.components.text.TextCompose
import com.ups.android.apps.designsystem.components.text.TextType
import com.ups.android.apps.designsystem.theme.LocalDimensions
import com.ups.android.apps.designsystem.theme.LocalShapes


@Composable
fun TabItem(
    text: String,
    isSelected: Boolean,
    background: Color,
    modifier: Modifier,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .background(color = background, shape = LocalShapes.current.button)
            .clickable(
                onClick = { onClick() }
            )
            .padding(vertical = LocalDimensions.current.dimensions12),
        contentAlignment = Alignment.Center,
        content = {

            TextCompose(
                text = text,
                textType = if (isSelected) TextType.BUTTON_LABEL else
                    TextType.BUTTON_INACTIVE
            )
        })
}


@Composable
fun AnimatedTab(
    tabList: Array<String>,
    onClick: (Int) -> Unit
) {

    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        contentAlignment = Alignment.Center,
        content = {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    for ((index, tabText) in tabList.withIndex()) {
                        val isSelected = index == selectedItemIndex
                        val background: Color by animateColorAsState(
                            targetValue = if (isSelected) MaterialTheme.colorScheme.primary else
                                Color.Transparent,
                            animationSpec = tween(
                                durationMillis = 300,
                                easing = LinearOutSlowInEasing
                            ),
                            label = ""
                        )
                        TabItem(
                            text = tabText,
                            modifier = Modifier.weight(0.5f),
                            isSelected = isSelected,
                            background = background,
                            onClick = {
                                selectedItemIndex = index
                                onClick.invoke(selectedItemIndex)
                            }
                        )
                    }
                })
        })
}