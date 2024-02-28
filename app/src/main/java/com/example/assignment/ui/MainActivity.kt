package com.example.assignment.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.assignment.data.cache.DataManager
import com.example.assignment.data.model.Item
import com.example.assignment.data.model.NetworkResult
import com.example.assignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    lateinit var dataAdapter: DataAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val spanCount = if (isTablet()) 1 else 1
        binding.rvList.layoutManager = GridLayoutManager(this, spanCount)
        dataAdapter= DataAdapter()
        binding.rvList.adapter = dataAdapter

        dataAdapter.setItemClick(object : ClickInterface<Item> {
            override fun onClick(data: Item) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data.link))
                startActivity(intent)
            }
        })
        mainViewModel.dataResponse.observe(this) {
            when(it) {
                is NetworkResult.Loading -> {
                    binding.progressbar.isVisible = it.isLoading
                }

                is NetworkResult.Failure -> {
                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_SHORT).show()
                    binding.progressbar.isVisible = false
                }

                is  NetworkResult.Success -> {
                    dataAdapter.updateMovies(it.data)
                    binding.progressbar.isVisible = false
                    DataManager.init(this,it.data)
                    DataManager.fetchDataAndCache()
                }
            }
        }




    }
    private fun isTablet(): Boolean {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val screenWidth = displayMetrics.widthPixels
        val screenHeight = displayMetrics.heightPixels
        val screenSize = Math.sqrt((screenWidth.toDouble() * screenWidth + screenHeight * screenHeight).toDouble())
        return screenSize >= 900 // Adjust this threshold as needed
    }
}