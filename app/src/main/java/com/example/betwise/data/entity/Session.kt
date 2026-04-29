package com.example.betwise.data.entity

@Entity(tableName = "sessions")
data class Session(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val date: String,
    val duration: String,
    val amount: Double,
    val categoryId: Int
)