package com.ups.android.apps.feature.transaction.presentation.addtransaction.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ups.android.apps.designsystem.UiText
import com.ups.android.apps.designsystem.functional.stateInWhileActive
import com.ups.android.apps.feature.transaction.domain.TransactionRepository
import com.ups.android.apps.feature.transaction.domain.usecase.GetDefaultCategoryUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AddTransactionViewModel(
    private val transactionRepository: TransactionRepository,
    private val getDefaultCategoryUseCase: GetDefaultCategoryUseCase
) : ViewModel() {

    private val mutableSharedFlow: MutableSharedFlow<AddTransactionEffect> = MutableSharedFlow()
    val addTransactionEffect: SharedFlow<AddTransactionEffect>
        get() = mutableSharedFlow.asSharedFlow()


    private val mutableUiState: MutableStateFlow<AddTransactionUiState> =
        MutableStateFlow(value = AddTransactionUiState.Initial)
    val state:  StateFlow<AddTransactionUiState>
        get() =mutableUiState.stateInWhileActive(
            scope = viewModelScope,
            initial = AddTransactionUiState.Initial,
            preFetch = {
                event(AddTransactionEvent.LoadCategories(isIncome = true))
            }
        )

    fun event(event: AddTransactionEvent) {
        when (event) {
            is AddTransactionEvent.LoadCategories -> loadCategories(event)
            is AddTransactionEvent.SaveTransaction -> saveTransaction(event)
            is AddTransactionEvent.UpdateCategories -> updateCategories(event)
        }
    }

    private fun saveTransaction(event: AddTransactionEvent.SaveTransaction) = viewModelScope.launch {
        transactionRepository.saveTransaction(event.transaction)
        mutableSharedFlow.emit(value = AddTransactionEffect.SuccessSuccessBanner(
            text = UiText.SimpleString("Updated.")
        ))
    }

    private fun loadCategories(event: AddTransactionEvent.LoadCategories) {
        viewModelScope.launch {
            val categories = getDefaultCategoryUseCase(isIncome = event.isIncome)
            mutableUiState.value = AddTransactionUiState.CategoriesLoaded(
                categories = categories
            )
        }
    }

    private fun updateCategories(event: AddTransactionEvent.UpdateCategories) {
        viewModelScope.launch {
            val categories = getDefaultCategoryUseCase(isIncome = event.isIncome)
            mutableSharedFlow.emit(
                value = AddTransactionEffect.CategoriesUpdated(categories)
            )
        }
    }
}