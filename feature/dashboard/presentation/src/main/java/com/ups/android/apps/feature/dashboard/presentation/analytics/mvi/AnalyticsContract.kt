package com.ups.android.apps.feature.dashboard.presentation.analytics.mvi

import com.ups.android.apps.common.model.feature.AnalyticsData


data class AnalyticsUiState(
    val isLoading: Boolean = true,
    val analyticsData: AnalyticsData = AnalyticsData()
)

sealed interface AnalyticsEvent {
    object LoadData : AnalyticsEvent
}
