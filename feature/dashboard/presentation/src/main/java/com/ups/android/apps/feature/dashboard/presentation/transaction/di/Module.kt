package com.ups.android.apps.feature.dashboard.presentation.transaction.di

import com.ups.android.apps.feature.dashboard.domain.usecase.GetTransactionUseCase
import com.ups.android.apps.feature.dashboard.presentation.transaction.mvi.TransactionViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val transactionPresentationModule = module {
    single { GetTransactionUseCase(dashboardRepository = get()) }
    viewModel {
        TransactionViewModel(useCase = get())
    }
}