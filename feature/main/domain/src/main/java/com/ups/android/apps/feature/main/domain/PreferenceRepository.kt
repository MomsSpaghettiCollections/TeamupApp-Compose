package com.ups.android.apps.feature.main.domain

import com.ups.android.apps.common.model.UserPref
import kotlinx.coroutines.flow.Flow

interface PreferenceRepository {
    fun getUserPreference(): Flow<UserPref>
}