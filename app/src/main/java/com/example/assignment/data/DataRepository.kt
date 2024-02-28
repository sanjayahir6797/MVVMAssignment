package com.example.assignment.data

import com.example.assignment.data.model.JsonResponse
import com.example.assignment.data.model.NetworkResult
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class DataRepository {

    suspend fun getData()= flow {
        emit(NetworkResult.Loading(true))
        val response = RetrofitInstance.apiService.fetchData()
        if(response.items!=null) {
            emit(NetworkResult.Success( response.items!!.sortedBy { it.published }))
        }
    }.catch{ e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }
}
