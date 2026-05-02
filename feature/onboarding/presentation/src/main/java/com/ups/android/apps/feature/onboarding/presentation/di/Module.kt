package com.ups.android.apps.feature.onboarding.presentation.di

import com.ups.android.apps.feature.onboarding.presentation.mvi.OnBoardingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val onBoardingPresentationModule = module {
    viewModel {
        OnBoardingViewModel(
            preferenceRepository = get(),
            authRepository = get()
        )
    }
}