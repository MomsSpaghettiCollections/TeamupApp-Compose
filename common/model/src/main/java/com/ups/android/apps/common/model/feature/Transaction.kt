package com.ups.android.apps.common.model.feature

data class Transaction(
    val id: Int = 0,
    val type: ModelTransactionType,
    val categoryId: Int,
    val subcategoryId: Int,
    val date:Long,
    val amount: Double,
    val notes: String,
)

enum class ModelTransactionType {
    DEBIT,
    CREDIT
}
