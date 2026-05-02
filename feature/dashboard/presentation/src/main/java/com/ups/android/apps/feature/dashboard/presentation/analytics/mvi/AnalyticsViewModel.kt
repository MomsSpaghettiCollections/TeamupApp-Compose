package com.ups.android.apps.feature.dashboard.presentation.analytics.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ups.android.apps.feature.dashboard.domain.usecase.GetAnalyticsDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnalyticsViewModel(
    private val getAnalyticsDataUseCase: GetAnalyticsDataUseCase
) : ViewModel() {

    private val mutableUiState: MutableStateFlow<AnalyticsUiState> =
        MutableStateFlow(value = AnalyticsUiState())
    val state: StateFlow<AnalyticsUiState>
        get() = mutableUiState.asStateFlow()


    fun event(event: AnalyticsEvent) {
        when (event) {
            AnalyticsEvent.LoadData -> loadData()
        }
    }

    private fun loadData() = viewModelScope.launch {
        val data = getAnalyticsDataUseCase()
        mutableUiState.value = mutableUiState.value.copy(
            isLoading = false,
            analyticsData = data
        )
    }
}