package com.ups.android.apps.feature.main.data.di


import com.ups.android.apps.feature.main.data.repository.PreferenceRepositoryImpl
import com.ups.android.apps.feature.main.domain.PreferenceRepository
import com.ups.android.apps.storage.datastore.di.userPreferenceModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val mainDataModule = module {

    includes(userPreferenceModule)

    singleOf(::PreferenceRepositoryImpl) bind PreferenceRepository::class
}