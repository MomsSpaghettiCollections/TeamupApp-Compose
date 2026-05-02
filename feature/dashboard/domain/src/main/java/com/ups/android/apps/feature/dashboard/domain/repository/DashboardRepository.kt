package com.ups.android.apps.feature.dashboard.domain.repository

import com.ups.android.apps.common.model.feature.Transaction

interface DashboardRepository {
    suspend fun getTransactions(): List<Transaction>
}