package com.ups.android.apps.feature.dashboard.presentation.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ups.android.apps.designsystem.components.text.NotificationSettingItem
import com.ups.android.apps.designsystem.components.text.SettingsItem
import com.ups.android.apps.designsystem.components.text.TextCompose
import com.ups.android.apps.designsystem.components.text.TextType
import com.ups.android.apps.designsystem.theme.LocalDimensions
import com.ups.android.apps.feature.dashboard.presentation.settings.mvi.SettingType
import com.ups.android.apps.feature.dashboard.presentation.settings.mvi.SettingsViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun SettingScreen(
    viewModel: SettingsViewModel = koinViewModel<SettingsViewModel>(),
    onSelect: () -> Unit
) {

    val state = viewModel.state.collectAsStateWithLifecycle().value

    Column(
        modifier = Modifier.padding(
            top = 16.dp,
            start = 16.dp,
            end = 16.dp
        ),
        content = {
            Text("Settings")

            LazyColumn(
                modifier = Modifier.padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(LocalDimensions.current.dimensions16),
                content = {

                    items(items = state.settings, itemContent = { item ->

                        when (item) {
                            is SettingType.Header -> TextCompose(
                                text = item.headerTitle,
                                textType = TextType.HEADLINE_MEDIUM
                            )
                            is SettingType.Item -> SettingsItem(
                                title = item.itemTitle,
                                onClick = {

                                }
                            )
                            is SettingType.Switch -> NotificationSettingItem(
                                title = item.switchTitle
                            )
                        }
                    })
                }
            )
        }
    )

}