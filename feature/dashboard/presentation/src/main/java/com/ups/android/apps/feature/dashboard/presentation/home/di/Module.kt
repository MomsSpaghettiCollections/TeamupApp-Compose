package com.ups.android.apps.feature.dashboard.presentation.home.di

import com.ups.android.apps.feature.dashboard.domain.usecase.GetHomeDataUseCase
import com.ups.android.apps.feature.dashboard.presentation.home.mvi.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val homePresentationModule = module {
    single {
        GetHomeDataUseCase(get())
    }
    viewModel {
        HomeViewModel(get())
    }
}