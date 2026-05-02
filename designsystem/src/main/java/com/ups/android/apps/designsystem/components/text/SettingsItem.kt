package com.ups.android.apps.designsystem.components.text

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ups.android.apps.designsystem.theme.LocalDimensions


@Composable
fun SettingsItem(
    title: String,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {
                onClick()
            })
            .padding(
                vertical = LocalDimensions.current.dimensions12
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        content = {
            TextCompose(
                text = title,
                textType = TextType.HEADLINE_LARGE
            )
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(
                    size = 16.dp
                )
            )
        }
    )

}

@Composable
fun NotificationSettingItem(
    title: String,
) {
    var isChecked by remember { mutableStateOf(true) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = LocalDimensions.current.dimensions12
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        content = {
            TextCompose(
                text = title,
                textType = TextType.HEADLINE_LARGE
            )
            Switch(
                checked = isChecked,
                onCheckedChange = {
                    isChecked = it
                },
                colors = SwitchDefaults.colors(
                    checkedIconColor = MaterialTheme.colorScheme.primary,
                    checkedThumbColor = Color.White
                )
            )
        }
    )

}