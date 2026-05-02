package com.ups.android.apps.storage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.ups.android.apps.storage.database.entity.CategoryEntity
import com.ups.android.apps.storage.database.entity.CategoryWithSubcategories
import com.ups.android.apps.storage.database.entity.SubcategoryEntity
import com.ups.android.apps.storage.database.entity.TransactionEntity

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionEntity)

    @Query("SELECT * FROM transactions")
    suspend fun getAllTransaction(): List<TransactionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubcategory(subcategory: SubcategoryEntity)

    @Transaction
    @Query("SELECT * FROM categories")
    suspend fun getCategoriesWithSubcategories(): List<CategoryWithSubcategories>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSubcategory(subcategory: SubcategoryEntity)
}