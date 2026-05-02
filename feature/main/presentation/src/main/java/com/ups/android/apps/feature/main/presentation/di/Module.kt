package com.ups.android.apps.feature.main.presentation.di

import com.ups.android.apps.feature.dashboard.presentation.analytics.di.analyticsPresentationModule
import com.ups.android.apps.feature.dashboard.presentation.home.di.homePresentationModule
import com.ups.android.apps.feature.dashboard.presentation.settings.di.settingsPresentationModule
import com.ups.android.apps.feature.dashboard.presentation.transaction.di.transactionPresentationModule
import com.ups.android.apps.feature.main.presentation.mvi.MainViewModel
import com.ups.android.apps.feature.onboarding.presentation.di.onBoardingPresentationModule
import com.ups.android.apps.feature.transaction.presentation.addtransaction.di.addTransactionPresentationModule
import com.ups.android.apps.feature.transaction.presentation.selectcategory.di.categoryPresentationModule
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val mainPresentationModule = module {
    includes(homePresentationModule)
    includes(onBoardingPresentationModule)
    includes(addTransactionPresentationModule)
    includes(categoryPresentationModule)
    includes(analyticsPresentationModule)
    includes(settingsPresentationModule)
    includes(transactionPresentationModule)

    viewModel {
        MainViewModel(preferenceRepository = get())
    }
}