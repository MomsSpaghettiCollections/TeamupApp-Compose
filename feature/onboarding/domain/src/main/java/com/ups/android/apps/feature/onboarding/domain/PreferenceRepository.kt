package com.ups.android.apps.feature.onboarding.domain

import com.ups.android.apps.common.model.AuthConfig
import com.ups.android.apps.common.model.OnBoardingConfig

interface PreferenceRepository {
    suspend fun saveUserAuth(authConfig: AuthConfig)
    suspend fun saveUserOnboardingStatus(onBoardingConfig: OnBoardingConfig)
}