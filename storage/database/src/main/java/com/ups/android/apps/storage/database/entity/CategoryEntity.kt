package com.ups.android.apps.storage.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ups.android.apps.storage.database.TransactionType

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val icon: String,
    val type: TransactionType
)