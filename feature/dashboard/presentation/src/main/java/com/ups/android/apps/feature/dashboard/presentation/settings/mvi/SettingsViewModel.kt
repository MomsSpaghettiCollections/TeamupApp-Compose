package com.ups.android.apps.feature.dashboard.presentation.settings.mvi

import androidx.lifecycle.ViewModel
import com.ups.android.apps.feature.dashboard.domain.repository.UserSettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed interface SettingType {
    data class Header(val headerTitle: String) : SettingType
    data class Item(val itemTitle: String) : SettingType
    data class Switch(val switchTitle: String) : SettingType
}

data class Initial(val settings: List<SettingType> = emptyList<SettingType>())

class SettingsViewModel(
    private val repository: UserSettingsRepository
): ViewModel() {

    private val mutableUiState : MutableStateFlow<Initial> = MutableStateFlow(Initial(getList()))
    val state : StateFlow<Initial>
        get() = mutableUiState.asStateFlow()

    private fun getList(): List<SettingType> =
        listOf(
            SettingType.Header("Account"),
            SettingType.Item("Default Currency"),
            SettingType.Item("Default Currency"),
            SettingType.Item("Default Currency"),
            SettingType.Header("Account"),
            SettingType.Switch("Notification"),
            SettingType.Item("Default Currency")
        )
}
