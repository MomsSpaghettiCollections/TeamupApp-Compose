package com.ups.android.apps.common.model

import com.ups.android.apps.common.model.feature.ModelTransactionType
import kotlinx.serialization.Serializable

@Serializable
data class Categorization(
    val type: ModelTransactionType,
    val category: Category,
    val subcategories: List<Category>
)

@Serializable
data class Category(
    val name: String, val icon: String, val id: Int = 0
)