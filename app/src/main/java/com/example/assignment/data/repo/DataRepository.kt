package com.example.assignment.data.repo

import android.content.Context
import androidx.room.withTransaction
import com.example.assignment.utils.networkBoundResource
import com.example.assignment.data.cache.DatabaseProvider
import com.example.assignment.data.model.Item
import com.example.assignment.data.model.NetworkResult
import com.example.assignment.data.remote.RetrofitInstance
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class DataRepository(private val context: Context) {

    private val roomDatabase = DatabaseProvider.getInstance(context)
    private val dataDao = roomDatabase.itemDataDao()

    private val apiService = RetrofitInstance.apiService // Your network service

    fun getItems() = networkBoundResource(
        query = {
                dataDao!!.getAllItems()
        },
        fetch = {
            delay(2000)
            apiService.fetchData()
        },
        saveFetchResult = { items ->
            roomDatabase.withTransaction {
                dataDao?.deleteAllItems()
                items.items?.sortedBy { it.published }?.let { dataDao?.insertItems(it) }

            }
        }
    )


}
