package com.example.assignment.ui

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.assignment.MyApp
import com.example.assignment.R
import com.example.assignment.data.cache.DataManager
import com.example.assignment.data.cache.DataManager.getCachedData
import com.example.assignment.data.cache.MyAppDatabase
import com.example.assignment.data.model.Item
import com.example.assignment.data.model.NetworkResult
import com.example.assignment.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.Executor

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    lateinit var dataAdapter: DataAdapter
    private var gridLayoutManager: GridLayoutManager ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gridLayoutManager = GridLayoutManager(this, resources.getInteger(R.integer.grid_column_count))
        binding.rvList.layoutManager = gridLayoutManager
        dataAdapter = DataAdapter()
        binding.rvList.adapter = dataAdapter

        dataAdapter.setItemClick(object : ClickInterface<Item> {
            override fun onClick(data: Item) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data.link))
                startActivity(intent)
            }
        })
        mainViewModel.dataResponse.observe(this) {
            when (it) {
                is NetworkResult.Loading -> {
                    binding.progressbar.isVisible = it.isLoading
                }

                is NetworkResult.Failure -> {
                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_SHORT).show()
                    binding.progressbar.isVisible = false
                }

                is NetworkResult.Success -> {
                    dataAdapter.updateItems(it.data)
                    binding.progressbar.isVisible = false
                    DataManager.init()
                   DataManager.addCacheData(this,it.data)

                }
            }


        }
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        gridLayoutManager?.spanCount = resources.getInteger(R.integer.grid_column_count)
    }
}