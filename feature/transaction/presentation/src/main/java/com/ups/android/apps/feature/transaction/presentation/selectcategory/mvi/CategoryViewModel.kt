package com.ups.android.apps.feature.transaction.presentation.selectcategory.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ups.android.apps.designsystem.functional.stateInWhileActive
import com.ups.android.apps.feature.transaction.domain.usecase.GetCategoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val getCategoryUseCase: GetCategoryUseCase
) : ViewModel() {

    private val mutableUiState: MutableStateFlow<CategoryUiState> =
        MutableStateFlow(value = CategoryUiState.Initial)
    val state: StateFlow<CategoryUiState>
        get() = mutableUiState.stateInWhileActive(
            scope = viewModelScope,
            initial = CategoryUiState.Initial,
            preFetch = {

            }
        )


    fun event(event: CategoryEvent) {
        when (event) {
            is CategoryEvent.Initialize -> viewModelScope.launch {
                val categories = getCategoryUseCase(isIncome = event.isIncome)
                mutableUiState.value = CategoryUiState.CategoriesLoaded(
                    categories = categories
                )
            }
        }
    }
}