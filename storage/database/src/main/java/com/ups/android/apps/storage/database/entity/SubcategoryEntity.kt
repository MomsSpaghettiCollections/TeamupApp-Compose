package com.ups.android.apps.storage.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subcategories")
data class SubcategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val categoryId: Int,
    val name: String,
    val icon: String
)