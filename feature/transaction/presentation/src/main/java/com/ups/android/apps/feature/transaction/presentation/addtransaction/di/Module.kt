package com.ups.android.apps.feature.transaction.presentation.addtransaction.di

import com.ups.android.apps.feature.transaction.domain.usecase.GetDefaultCategoryUseCase
import com.ups.android.apps.feature.transaction.presentation.addtransaction.mvi.AddTransactionViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val addTransactionPresentationModule = module {
    single {
        GetDefaultCategoryUseCase(
            repository = get()
        )
    }
    viewModel {
        AddTransactionViewModel(
            transactionRepository = get(),
            getDefaultCategoryUseCase = get()
        )
    }
}