package com.example.betwise.data.db

import android.content.Context
import androidx.room.*
import com.example.betwise.data.dao.*
import com.example.betwisespp.data.entity.*

@Database(
    entities = [User::class, Category::class, Session::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun categoryDao(): CategoryDao
    abstract fun sessionDao(): SessionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "betwise_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}