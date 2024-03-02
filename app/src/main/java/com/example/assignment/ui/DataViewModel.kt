package com.example.assignment.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import com.example.assignment.data.repo.DataRepository


class DataViewModel(application: Application) : AndroidViewModel(application) {

   private val dataRepository = DataRepository(application)
    val items = dataRepository.getItems().asLiveData()
}