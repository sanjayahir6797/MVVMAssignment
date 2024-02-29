package com.example.assignment.data.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.assignment.data.model.Item

@Database(entities = [Item::class], version = 1)
@TypeConverters(Converters::class)
abstract class MyAppDatabase : RoomDatabase() {
    abstract fun itemDataDao(): ItemDao?
    companion object{
        @Volatile   private var inastance: MyAppDatabase? =null
        private val LOCK = Any()

        operator  fun invoke(context: Context): MyAppDatabase {
            return inastance ?: synchronized(LOCK){
                inastance ?: Builddatabase(context).also {
                    inastance=it
                }
            }
        }
        private fun Builddatabase(context: Context)= Room.databaseBuilder(context.applicationContext,MyAppDatabase::class.java,"Taskbardb").build()
    }
}