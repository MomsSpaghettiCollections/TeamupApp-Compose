package com.ups.android.apps.feature.transaction.data.repository

import com.ups.android.apps.common.model.Categorization
import com.ups.android.apps.common.model.Category
import com.ups.android.apps.common.model.feature.ModelTransactionType
import com.ups.android.apps.common.model.feature.Transaction
import com.ups.android.apps.feature.transaction.domain.TransactionRepository
import com.ups.android.apps.storage.database.TransactionType
import com.ups.android.apps.storage.database.dao.TransactionDao
import com.ups.android.apps.storage.database.entity.TransactionEntity
import kotlinx.coroutines.delay

class TransactionRepositoryImpl(
    private val dao: TransactionDao
) : TransactionRepository {

    override suspend fun saveTransaction(transaction: Transaction) {
        dao.insertTransaction(
            transaction = TransactionEntity(
                type = when (transaction.type) {
                    ModelTransactionType.CREDIT -> TransactionType.CREDIT
                    ModelTransactionType.DEBIT -> TransactionType.DEBIT
                },
                categoryId = transaction.categoryId,
                subcategoryId = transaction.subcategoryId,
                notes = transaction.notes,
                date = transaction.date,
                amount = transaction.amount
            )
        )
    }

    override suspend fun getCategoriesWithSubcategories(): List<Categorization> {
        var categories = dao.getCategoriesWithSubcategories()
        while (categories.isEmpty()) {
            delay(timeMillis = 100)
            categories = dao.getCategoriesWithSubcategories()
        }
        return categories.map {
            Categorization(
                type = when (it.category.type) {
                    TransactionType.CREDIT -> ModelTransactionType.CREDIT
                    TransactionType.DEBIT -> ModelTransactionType.DEBIT
                },
                category = Category(
                    id = it.category.id,
                    name = it.category.name,
                    icon = it.category.icon
                ),
                subcategories = it.subcategories.map { subcategory ->
                    Category(
                        id = subcategory.id,
                        name = subcategory.name,
                        icon = subcategory.icon
                    )
                }
            )
        }
    }
}