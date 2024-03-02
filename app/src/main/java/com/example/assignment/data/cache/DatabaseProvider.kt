package com.example.assignment.data.cache

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    private var instance: MyAppDatabase? = null

    fun getInstance(context: Context): MyAppDatabase {
        return instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }
    }
    private fun buildDatabase(context: Context): MyAppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MyAppDatabase::class.java, "your_database_name.db"
        ).build()
    }
}
