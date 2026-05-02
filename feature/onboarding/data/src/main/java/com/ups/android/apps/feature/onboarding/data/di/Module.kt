package com.ups.android.apps.feature.onboarding.data.di


import com.ups.android.apps.feature.onboarding.data.repository.AuthRepositoryImpl
import com.ups.android.apps.feature.onboarding.data.repository.PreferenceRepositoryImpl
import com.ups.android.apps.feature.onboarding.domain.AuthRepository
import com.ups.android.apps.feature.onboarding.domain.PreferenceRepository
import com.ups.android.apps.network.firebase_auth.di.firebaseAuthModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val onboardingDataModule = module {
    includes(firebaseAuthModule)
    singleOf(::AuthRepositoryImpl) bind AuthRepository::class
    singleOf(::PreferenceRepositoryImpl) bind PreferenceRepository::class
}