package com.ups.android.apps.feature.transaction.domain.usecase

import com.ups.android.apps.common.model.feature.ModelTransactionType
import com.ups.android.apps.feature.transaction.domain.TransactionRepository

class GetCategoryUseCase(
    private val repository: TransactionRepository
) {
    suspend operator fun invoke(isIncome: Boolean) =
        repository.getCategoriesWithSubcategories().filter {
            if (isIncome) {
                it.type == ModelTransactionType.CREDIT
            } else {
                it.type == ModelTransactionType.DEBIT
            }
        }
}