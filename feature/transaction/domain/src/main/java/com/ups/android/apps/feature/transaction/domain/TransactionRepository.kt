package com.ups.android.apps.feature.transaction.domain

import com.ups.android.apps.common.model.Categorization
import com.ups.android.apps.common.model.feature.Transaction

interface TransactionRepository {
    suspend fun saveTransaction(transaction: Transaction)
    suspend fun getCategoriesWithSubcategories(): List<Categorization>
}