package com.ups.android.apps.feature.dashboard.presentation.settings.di

import com.ups.android.apps.feature.dashboard.presentation.settings.mvi.SettingsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val settingsPresentationModule = module {
    viewModel {
        SettingsViewModel(repository = get())
    }
}