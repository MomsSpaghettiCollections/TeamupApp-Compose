package com.ups.android.apps.feature.transaction.presentation.selectcategory.mvi

import com.ups.android.apps.common.model.Categorization


sealed interface CategoryEvent {
    data class Initialize(val isIncome: Boolean) : CategoryEvent
}

sealed interface CategoryUiState {
    object Initial : CategoryUiState
    data class CategoriesLoaded(val categories: List<Categorization>) : CategoryUiState
}

sealed interface CategoryEffect {
    data class NavigateBack(val category: Categorization) : CategoryEffect
}