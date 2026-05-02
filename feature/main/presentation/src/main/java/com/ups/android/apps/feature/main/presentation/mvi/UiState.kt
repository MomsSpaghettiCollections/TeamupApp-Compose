package com.ups.android.apps.feature.main.presentation.mvi

import com.ups.android.apps.common.model.UserPref


sealed interface UiState {
    data object Loading : UiState
    data class Success(val data: UserPref) : UiState
}