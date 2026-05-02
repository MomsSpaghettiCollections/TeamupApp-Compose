package com.ups.android.apps.storage.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithSubcategories(
    @Embedded val category: CategoryEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "categoryId"
    )
    val subcategories: List<SubcategoryEntity>
)
