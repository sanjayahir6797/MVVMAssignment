package com.example.assignment.ui

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.data.DataRepository
import com.example.assignment.data.cache.DataManager
import com.example.assignment.data.model.Item
import com.example.assignment.data.model.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val dataRepository = DataRepository()

  //  private val _data = MutableStateFlow<NetworkResult<List<Item>>>()
  //  val data: StateFlow<NetworkResult<List<Item>>> get() = _data



    private var _dataResponse = MutableLiveData<NetworkResult<List<Item>>>()
    val dataResponse: LiveData<NetworkResult<List<Item>>> = _dataResponse

    init {
        fetchData()
    }
    private fun fetchData() {
        viewModelScope.launch {
            dataRepository.getData().collect{
                    _dataResponse.postValue(it)
            }

        }
    }
}
