package com.ups.android.apps.common.model.feature

data class IncomeExpenseData(
    val total: Double = 0.0,
    val graphData: List<Float> = emptyList()
)
data class MonthlyData(
    val label: String,
    val income: Int,
    val expense: Int
)
data class AnalyticsData(
    val incomeData: IncomeExpenseData = IncomeExpenseData(),
    val expenseData: IncomeExpenseData = IncomeExpenseData(),
    val monthlyData: List<MonthlyData> = emptyList(),
)