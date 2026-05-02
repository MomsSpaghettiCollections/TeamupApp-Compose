package com.ups.android.apps.feature.dashboard.presentation.home.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ups.android.apps.feature.dashboard.domain.usecase.GetHomeDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCase: GetHomeDataUseCase
) : ViewModel() {

    val mutableUiState: MutableStateFlow<HomeUIState> = MutableStateFlow(value = HomeUIState())
    val state: StateFlow<HomeUIState>
        get() = mutableUiState.asStateFlow()


    fun event(event: HomeEvent) {
        when (event) {
            HomeEvent.LoadData -> loadData()
        }
    }

    private fun loadData() = viewModelScope.launch {
        val data = useCase()
        mutableUiState.update {
            it.copy(
                isLoading = false,
                isLEmpty = data.transactionList.isEmpty(),
                homeData = data
            )
        }
    }
}