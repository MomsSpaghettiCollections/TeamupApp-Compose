package com.ups.android.apps.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ups.android.apps.storage.database.dao.TransactionDao
import com.ups.android.apps.storage.database.entity.CategoryEntity
import com.ups.android.apps.storage.database.entity.CategoryWithSubcategories
import com.ups.android.apps.storage.database.entity.SubcategoryEntity
import com.ups.android.apps.storage.database.entity.TransactionEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.text.toInt

@Database(
    entities = [
        TransactionEntity::class,
        CategoryEntity::class,
        SubcategoryEntity::class
    ],
    version = 1
)
abstract class TeamupDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao

    companion object {
        fun prepopulateDatabase(database: TeamupDatabase) {
            CoroutineScope(Dispatchers.IO).launch {
                val dao = database.transactionDao()
                val categories = getPredefinedCategories()
                categories.forEach { categoryWithSubcategories ->
                    val categoryId = dao.insertCategory(categoryWithSubcategories.category).toInt()
                    categoryWithSubcategories.subcategories.forEach { subcategory ->
                        dao.insertSubcategory(
                            subcategory.copy(categoryId = categoryId)
                        )
                    }
                }
            }
        }
    }
}

fun getPredefinedCategories(): List<CategoryWithSubcategories> {
    return listOf(
        CategoryWithSubcategories(
            category = CategoryEntity(name = "ONE", icon = "", type = TransactionType.CREDIT),
            subcategories = listOf(
                SubcategoryEntity(categoryId = 1, name = "ONE ITEM1", icon = ""),
                SubcategoryEntity(categoryId = 1, name = "ONE ITEM2", icon = ""),
            )
        ),
        CategoryWithSubcategories(
            category = CategoryEntity(name = "THREE", icon = "",type = TransactionType.CREDIT),
            subcategories = listOf(
                SubcategoryEntity(categoryId = 3, name = "THREE ITEM1", icon = ""),
                SubcategoryEntity(categoryId = 3, name = "THREE ITEM2", icon = ""),
            )
        ),
        CategoryWithSubcategories(
            category = CategoryEntity(name = "TWO", icon = "", type = TransactionType.DEBIT),
            subcategories = listOf(
                SubcategoryEntity(categoryId = 2, name = "TWO ITEM1", icon = ""),
                SubcategoryEntity(categoryId = 2, name = "TWO ITEM2", icon = ""),
            )
        ),
        CategoryWithSubcategories(
            category = CategoryEntity(name = "FOUR", icon = "", type = TransactionType.DEBIT),
            subcategories = listOf(
                SubcategoryEntity(categoryId = 4, name = "FOUR ITEM1", icon = ""),
                SubcategoryEntity(categoryId = 4, name = "FOUR ITEM2", icon = ""),
                SubcategoryEntity(categoryId = 4, name = "FOUR ITEM3", icon = ""),
            )
        ),
    )
}
