package com.ups.android.apps.feature.dashboard.presentation.analytics.di

import com.ups.android.apps.feature.dashboard.domain.usecase.GetAnalyticsDataUseCase
import com.ups.android.apps.feature.dashboard.presentation.analytics.mvi.AnalyticsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val analyticsPresentationModule = module {
    single { GetAnalyticsDataUseCase(dashboardRepository = get()) }
    viewModel {
        AnalyticsViewModel(
            getAnalyticsDataUseCase = get()
        )
    }
}