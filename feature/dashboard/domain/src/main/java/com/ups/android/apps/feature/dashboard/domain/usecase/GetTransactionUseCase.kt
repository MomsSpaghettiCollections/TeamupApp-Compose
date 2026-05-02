package com.ups.android.apps.feature.dashboard.domain.usecase

import com.ups.android.apps.common.model.feature.Transaction
import com.ups.android.apps.feature.dashboard.domain.repository.DashboardRepository

class GetTransactionUseCase(
    private val dashboardRepository: DashboardRepository
) {
    suspend operator fun invoke(): List<Transaction> {
        val recentTransactions = dashboardRepository.getTransactions()

        return recentTransactions
    }
}