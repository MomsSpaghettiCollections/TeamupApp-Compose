package com.ups.android.apps.designsystem.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.ups.android.apps.designsystem.components.text.TextCompose
import com.ups.android.apps.designsystem.components.text.TextType

data class BottomNavItemData(
    val index: Int,
    val title: String,
    val icon: ImageVector
)

@Composable
fun BottomNavBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {

    val items = remember {
        listOf(
            BottomNavItemData(0, "Dash", Icons.Default.Home),
            BottomNavItemData(1, "Record", Icons.Default.Home),
            BottomNavItemData(2, "Analytics", Icons.Default.Home),
            BottomNavItemData(3, "Settings", Icons.Default.Home),
            BottomNavItemData(4, "Add", Icons.Default.Home),
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer
            ), horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        content = {
            items.forEachIndexed { index, item ->
                val selected = selectedIndex == index

                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        onItemSelected(item.index)
                    },
                    icon = {
                        Icon(
                            tint = if (selected) MaterialTheme.colorScheme.primary else
                                MaterialTheme.colorScheme.onSurfaceVariant,
                            imageVector = item.icon,
                            modifier = Modifier.size(size = 16.dp),
                            contentDescription = null
                        )
                    },
                    label = {
                        TextCompose(
                            text = item.title,
                            textType = TextType.LABEL_SMALL
                        )
                    }
                )
            }
        }
    )
}