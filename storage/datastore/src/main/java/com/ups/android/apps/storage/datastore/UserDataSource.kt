package com.ups.android.apps.storage.datastore

import androidx.datastore.core.DataStore
import com.ups.android.apps.common.model.AuthConfig
import com.ups.android.apps.common.model.OnBoardingConfig
import com.ups.android.apps.storage.datastore.data.AuthStatus
import com.ups.android.apps.storage.datastore.data.OnboardingStatus
import com.ups.android.apps.storage.datastore.data.UserPreferences
import com.ups.android.apps.storage.datastore.data.copy
import kotlinx.coroutines.flow.Flow

class UserDataSource(
    private val datastore: DataStore<UserPreferences>
) {

    fun userData(): Flow<UserPreferences> {
        return datastore.data
    }

    suspend fun setAuthType(config: AuthConfig) {
        datastore.updateData {
            it.copy {
                authStatus = when (config) {
                    AuthConfig.NOT_AUTHENTICATED -> AuthStatus.NOT_AUTHENTICATED
                    AuthConfig.AUTHENTICATED -> AuthStatus.AUTHENTICATED
                    AuthConfig.GUEST -> AuthStatus.GUEST
                }
            }
        }
    }

    suspend fun setOnboardingType(config: OnBoardingConfig) {
        datastore.updateData {
            it.copy {
                onboardingStatus = when (config) {
                    OnBoardingConfig.NOT_STARTED -> OnboardingStatus.NOT_STARTED
                    OnBoardingConfig.IN_PROGRESS -> OnboardingStatus.IN_PROGRESS
                    OnBoardingConfig.COMPLETED -> OnboardingStatus.COMPLETED
                }
            }
        }
    }

    suspend fun setIsNotificationEnabled(enabled: Boolean) {
        datastore.updateData {
            it.copy {
                isNotificationEnabled = enabled
            }
        }
    }

}