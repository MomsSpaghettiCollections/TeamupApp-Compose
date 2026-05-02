package com.ups.android.apps.common.model.feature

data class Balance(
    val totalIncome: Double = 0.0,
    val totalExpense: Double = 0.0,
    val currentBalance: Double = 0.0
)

data class HomeData(
    val priceBalance: Balance = Balance(),
    val transactionList: List<Transaction> = emptyList()
)

