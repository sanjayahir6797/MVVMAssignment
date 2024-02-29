package com.example.assignment.data.cache

import android.content.Context
import androidx.room.Room
import com.example.assignment.MyApp
import com.example.assignment.data.model.Item
import java.util.concurrent.Executor
import java.util.concurrent.Executors

// Fetch and cache data
object DataManager {
    lateinit var executor: Executor
    //  private val myDataList: List<Item>? = null


    fun init() {

        //database = Room.databaseBuilder(context!!, MyAppDatabase::class.java, "my-database").build()
        executor = Executors.newSingleThreadExecutor()

    }

    fun addCacheData(context: Context, myDataList: List<Item>) {
        executor.execute {
            // Cache the fetched data in the local database
            MyAppDatabase(context).itemDataDao()?.deleteAllItems()
            MyAppDatabase(context).itemDataDao()?.insertAll(myDataList)
        }
    }

    fun getCachedData(context:Context):Int // List<Item>?
    {
        var sizeList=0
        executor.execute {
            sizeList= MyAppDatabase(context).itemDataDao()?.allItems?.size?:0
        }
    // Load cached data from the local database
    return sizeList
}
}