package com.example.assignment.ui

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.assignment.R
import com.example.assignment.data.model.Item
import com.example.assignment.databinding.ActivityMainBinding
import com.example.assignment.utils.Resource

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var dataAdapter: DataAdapter
    private var gridLayoutManager: GridLayoutManager ?=null

    private lateinit var viewModel: DataViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gridLayoutManager = GridLayoutManager(this, resources.getInteger(R.integer.grid_column_count))
        binding.rvList.layoutManager = gridLayoutManager
        dataAdapter = DataAdapter()
        binding.rvList.adapter = dataAdapter


        viewModel = ViewModelProvider(this)[DataViewModel::class.java]

        dataAdapter.setItemClick(object : ClickInterface<Item> {
            override fun onClick(data: Item) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data.link))
                startActivity(intent)
            }
        })

        viewModel.items.observe(this@MainActivity) { resource ->
            if(resource.data!=null) {
                dataAdapter.updateItems(resource.data)
            }
            binding.loadingProgressBar.isVisible =
                resource is Resource.Loading<*> && resource.data.isNullOrEmpty()
            binding.errorTextView.isVisible =
                resource is Resource.Error<*> && resource.data.isNullOrEmpty()
            binding.errorTextView.text = resource.error?.message
        }

    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        gridLayoutManager?.spanCount = resources.getInteger(R.integer.grid_column_count)
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities =
            connectivityManager.getNetworkCapabilities(network)
        return capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
    }
}