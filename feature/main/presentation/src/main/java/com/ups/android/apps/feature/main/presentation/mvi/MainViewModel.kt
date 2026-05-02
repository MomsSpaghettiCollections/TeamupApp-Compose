package com.ups.android.apps.feature.main.presentation.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ups.android.apps.feature.main.domain.PreferenceRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MainViewModel(
    preferenceRepository: PreferenceRepository
) : ViewModel() {

    val uiState: StateFlow<UiState> = preferenceRepository.getUserPreference().map { userPref ->
        UiState.Success(data = userPref)
    }.distinctUntilChanged().stateIn(
        scope = viewModelScope,
        initialValue = UiState.Loading,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000)
    )
}