package com.ups.android.apps.feature.dashboard.domain.usecase

import com.ups.android.apps.common.model.DateFormatPattern
import com.ups.android.apps.common.model.feature.AnalyticsData
import com.ups.android.apps.common.model.feature.IncomeExpenseData
import com.ups.android.apps.common.model.feature.ModelTransactionType
import com.ups.android.apps.common.model.feature.MonthlyData
import com.ups.android.apps.common.model.toDateFormat
import com.ups.android.apps.feature.dashboard.domain.repository.DashboardRepository

class GetAnalyticsDataUseCase(
    private val dashboardRepository: DashboardRepository
) {
    suspend operator fun invoke(): AnalyticsData {
        val recentTransactions = dashboardRepository.getTransactions()

        val (creditTransactions, debitTransactions) = recentTransactions.partition { it.type == ModelTransactionType.CREDIT }
        val totalIncome = creditTransactions.sumOf { it.amount }
        val totalExpense = debitTransactions.sumOf { it.amount }
        val monthlyData = recentTransactions.groupBy {
            it.date.toDateFormat(DateFormatPattern.SHORT)
        }.map {
            MonthlyData(
                label = it.key,
                income = it.value.filter { it.type == ModelTransactionType.CREDIT }
                    .sumOf { it.amount.toInt() },
                expense = it.value.filter { it.type == ModelTransactionType.DEBIT }
                    .sumOf { it.amount.toInt() },
            )
        }

        return  AnalyticsData(
            incomeData = IncomeExpenseData(
                total = totalIncome,
                graphData = creditTransactions.map {
                    it.amount.toFloat()
                }
            ),
            expenseData = IncomeExpenseData(
                total = totalExpense,
                graphData = debitTransactions.map {
                    it.amount.toFloat()
                }
            ),
            monthlyData = monthlyData
        )

    }
}