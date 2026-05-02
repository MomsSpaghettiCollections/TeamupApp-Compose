package com.ups.android.apps.feature.onboarding.data.repository

import com.ups.android.apps.common.model.AuthConfig
import com.ups.android.apps.common.model.OnBoardingConfig
import com.ups.android.apps.feature.onboarding.domain.PreferenceRepository
import com.ups.android.apps.storage.datastore.UserDataSource

class PreferenceRepositoryImpl(
    private val source: UserDataSource
) : PreferenceRepository {
    override suspend fun saveUserAuth(authConfig: AuthConfig) =
        source.setAuthType(authConfig)

    override suspend fun saveUserOnboardingStatus(onBoardingConfig: OnBoardingConfig) =
        source.setOnboardingType(onBoardingConfig)

}