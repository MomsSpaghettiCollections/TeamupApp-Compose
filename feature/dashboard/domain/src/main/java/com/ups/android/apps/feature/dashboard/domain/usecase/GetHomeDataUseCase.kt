package com.ups.android.apps.feature.dashboard.domain.usecase

import com.ups.android.apps.common.model.feature.Balance
import com.ups.android.apps.common.model.feature.HomeData
import com.ups.android.apps.common.model.feature.ModelTransactionType
import com.ups.android.apps.feature.dashboard.domain.repository.DashboardRepository

class GetHomeDataUseCase(
    private val dashboardRepository: DashboardRepository
) {
    suspend operator fun invoke(): HomeData {
        val recentTransactions = dashboardRepository.getTransactions()

        val totalPriceIncome =
            recentTransactions.filter { it.type == ModelTransactionType.CREDIT }.sumOf {
                it.amount
            }
        val totalPriceExpense =
            recentTransactions.filter { it.type == ModelTransactionType.DEBIT }.sumOf {
                it.amount
            }

        return HomeData(
            transactionList = recentTransactions,
            priceBalance = Balance(
                totalIncome = totalPriceIncome,
                totalExpense = totalPriceExpense,
                currentBalance = totalPriceIncome - totalPriceExpense
            )
        )
    }
}