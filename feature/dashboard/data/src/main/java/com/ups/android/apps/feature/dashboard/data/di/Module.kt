package com.ups.android.apps.feature.dashboard.data.di

import com.ups.android.apps.feature.dashboard.data.repository.DashboardRepositoryImpl
import com.ups.android.apps.feature.dashboard.data.repository.UserSettingsRepositoryImpl
import com.ups.android.apps.feature.dashboard.domain.repository.DashboardRepository
import com.ups.android.apps.feature.dashboard.domain.repository.UserSettingsRepository
import com.ups.android.apps.storage.database.di.roomModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val dashboardDataModule = module {
    includes(roomModule)
    singleOf(constructor = ::DashboardRepositoryImpl) bind DashboardRepository::class
    singleOf(constructor = ::UserSettingsRepositoryImpl) bind UserSettingsRepository::class
}