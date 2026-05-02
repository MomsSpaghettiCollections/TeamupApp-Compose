package com.ups.android.apps.feature.main.data.repository

import com.ups.android.apps.common.model.AuthConfig
import com.ups.android.apps.common.model.OnBoardingConfig
import com.ups.android.apps.common.model.UserPref
import com.ups.android.apps.feature.main.domain.PreferenceRepository
import com.ups.android.apps.storage.datastore.UserDataSource
import com.ups.android.apps.storage.datastore.data.AuthStatus
import com.ups.android.apps.storage.datastore.data.OnboardingStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class PreferenceRepositoryImpl(
    private val source: UserDataSource
) : PreferenceRepository {
    override fun getUserPreference(): Flow<UserPref> = flow {
        emit(
            value = source.userData().first().let {
                UserPref(
                    onBoardingStatus = when(it.onboardingStatus){
                        OnboardingStatus.NOT_STARTED -> OnBoardingConfig.NOT_STARTED
                        OnboardingStatus.IN_PROGRESS -> OnBoardingConfig.IN_PROGRESS
                        OnboardingStatus.COMPLETED -> OnBoardingConfig.COMPLETED
                        else -> OnBoardingConfig.NOT_STARTED
                    },
                    authStatus = when(it.authStatus){
                        AuthStatus.NOT_AUTHENTICATED -> AuthConfig.NOT_AUTHENTICATED
                        AuthStatus.AUTHENTICATED -> AuthConfig.AUTHENTICATED
                        AuthStatus.GUEST -> AuthConfig.GUEST
                        else -> AuthConfig.NOT_AUTHENTICATED
                    }
                )
            }
        )
    }
}