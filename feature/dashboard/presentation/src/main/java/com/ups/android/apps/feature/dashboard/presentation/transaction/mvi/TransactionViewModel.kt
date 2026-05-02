package com.ups.android.apps.feature.dashboard.presentation.transaction.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ups.android.apps.feature.dashboard.domain.usecase.GetTransactionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TransactionViewModel(
    private val useCase: GetTransactionUseCase
) : ViewModel() {

    private val mutableUiState: MutableStateFlow<TransactionUiState> = MutableStateFlow(
        value = TransactionUiState()
    )
    val state: StateFlow<TransactionUiState>
        get() = mutableUiState.asStateFlow()

    fun event(event: TransactionEvent) {
        when (event) {
            is TransactionEvent.FilterByText -> TODO()
            TransactionEvent.LoadTransaction -> loadTransaction()
        }
    }

    private fun loadTransaction() = viewModelScope.launch {
        mutableUiState.update {
            it.copy(isLoading = true)
        }
        val data = useCase()
        mutableUiState.update {
            it.copy(
                transaction = data,
                isLoading = false
            )
        }
    }
}