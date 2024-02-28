package com.example.assignment.data.cache

import android.content.Context
import androidx.room.Room
import com.example.assignment.MyApp
import com.example.assignment.data.model.Item
import java.util.concurrent.Executor
import java.util.concurrent.Executors

// Fetch and cache data
object DataManager {
   //lateinit var database: MyAppDatabase
    lateinit var executor: Executor
    private val myDataList: List<Item>? = null

    fun init(context: Context?, myDataList: List<Item?>?) {
        var myDataList = myDataList
        //database = Room.databaseBuilder(context!!, MyAppDatabase::class.java, "my-database").build()
        executor = Executors.newSingleThreadExecutor()
        myDataList = myDataList
    }

    fun fetchDataAndCache() {
        executor.execute {
            // Cache the fetched data in the local database
            MyApp.database.itemDataDao()?.deleteAllItems()
            MyApp.database.itemDataDao()?.insertAll(myDataList)
        }
    }

    val cachedData: List<Item>
        get() =// Load cached data from the local database
            MyApp.database.itemDataDao()!!.allItems
}