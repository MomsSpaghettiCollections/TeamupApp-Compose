package com.ups.android.apps.storage.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ups.android.apps.storage.database.TransactionType

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val type: TransactionType,
    val categoryId: Int,
    val subcategoryId: Int,
    val date: Long,
    val notes: String,
    val amount: Double
)