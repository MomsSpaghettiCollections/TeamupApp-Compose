package com.ups.android.apps.feature.dashboard.presentation.transaction.mvi

import com.ups.android.apps.common.model.feature.Transaction


data class TransactionUiState(
    val transaction: List<Transaction> = emptyList(),
    val isLoading: Boolean = true
)

sealed interface TransactionEvent {
    object LoadTransaction : TransactionEvent
    data class FilterByText(val text: String) : TransactionEvent
}
