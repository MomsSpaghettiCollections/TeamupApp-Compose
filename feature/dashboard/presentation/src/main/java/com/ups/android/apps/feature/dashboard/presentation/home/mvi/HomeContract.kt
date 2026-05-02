package com.ups.android.apps.feature.dashboard.presentation.home.mvi

import com.ups.android.apps.common.model.feature.HomeData


sealed interface HomeEvent {
    object LoadData : HomeEvent
}

data class HomeUIState(
    val isLoading: Boolean = true,
    val isLEmpty: Boolean = false,
    val homeData: HomeData = HomeData()
)