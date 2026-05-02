package com.ups.android.apps.feature.transaction.presentation.addtransaction.mvi

import com.ups.android.apps.common.model.Categorization
import com.ups.android.apps.common.model.feature.Transaction
import com.ups.android.apps.designsystem.UiText


sealed class AddTransactionEvent{
    data class SaveTransaction(val transaction: Transaction) : AddTransactionEvent()
    data class LoadCategories(val isIncome: Boolean): AddTransactionEvent()
    data class UpdateCategories(val isIncome: Boolean): AddTransactionEvent()
}

sealed class AddTransactionEffect {
    data class SuccessSuccessBanner(val text: UiText) : AddTransactionEffect()
    data class CategoriesUpdated(val categories: Categorization): AddTransactionEffect()
}

sealed class AddTransactionUiState{
    data object Initial : AddTransactionUiState()
    data class CategoriesLoaded(val categories: Categorization): AddTransactionUiState()
}
