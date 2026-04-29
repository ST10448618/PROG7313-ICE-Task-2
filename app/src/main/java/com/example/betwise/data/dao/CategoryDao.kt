package com.example.betwise.data.dao

import androidx.room.*
import com.example.betwise.data.entity.Category

@Dao
interface CategoryDao {

    @Insert
    suspend fun insert(category: Category)

    @Query("SELECT * FROM categories")
    suspend fun getAll(): List<Category>
}