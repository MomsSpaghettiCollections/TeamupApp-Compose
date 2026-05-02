package com.ups.android.apps.teamup

import android.app.Application
import com.ups.android.apps.feature.dashboard.data.di.dashboardDataModule
import com.ups.android.apps.feature.main.data.di.mainDataModule
import com.ups.android.apps.feature.main.presentation.di.mainPresentationModule
import com.ups.android.apps.feature.onboarding.data.di.onboardingDataModule
import com.ups.android.apps.feature.transaction.data.di.addTransactionDataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class TeamupApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TeamupApp)
            modules(
                mainDataModule,
                mainPresentationModule,
                onboardingDataModule,
                dashboardDataModule,
                addTransactionDataModule
            )
        }
    }
}