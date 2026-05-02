package com.ups.android.apps.feature.dashboard.data.repository

import com.ups.android.apps.common.model.feature.ModelTransactionType
import com.ups.android.apps.common.model.feature.Transaction
import com.ups.android.apps.feature.dashboard.domain.repository.DashboardRepository
import com.ups.android.apps.storage.database.TransactionType
import com.ups.android.apps.storage.database.dao.TransactionDao

class DashboardRepositoryImpl(
    private val dao: TransactionDao
) : DashboardRepository {
    override suspend fun getTransactions(): List<Transaction> {
        return dao.getAllTransaction().map {
            Transaction(
                type = when (it.type) {
                    TransactionType.CREDIT -> ModelTransactionType.CREDIT
                    TransactionType.DEBIT -> ModelTransactionType.DEBIT
                },
                categoryId = it.categoryId,
                subcategoryId = it.subcategoryId,
                date = it.date,
                notes = it.notes,
                amount = it.amount
            )
        }.reversed()
    }
}