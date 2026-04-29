package com.example.betwise.data.dao

import androidx.room.*
import com.example.betwise.data.entity.Session

@Dao
interface SessionDao {

    @Insert
    suspend fun insert(session: Session)

    @Query("SELECT * FROM sessions WHERE categoryId = :categoryId")
    suspend fun getByCategory(categoryId: Int): List<Session>
}