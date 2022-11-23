package com.cloudin.task.ui.home

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cloudin.task.R
import com.cloudin.task.databinding.ActivityMainBinding
import com.cloudin.task.ui.adapter.UserAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var userAdapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.apply {
            progressIndicator.visibility = View.VISIBLE
            noUsers.visibility = View.GONE
            recyclerView.visibility = View.GONE
            recyclerView.adapter = userAdapter
        }
        if (isOnline())
            setUpObservers()
        else
            Toast.makeText(this, resources.getText(R.string.check_internet), Toast.LENGTH_SHORT)
                .show()

    }

    private fun setUpObservers() {
        viewModel.userData.observe(this) { data ->
            if (data.isSuccessful && data.code() == 200) {
                binding.apply {
                    progressIndicator.pauseAnimation()
                    progressIndicator.visibility = View.GONE
                    noUsers.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                }
                userAdapter.updateItems(data.body()?.results)
            } else {
                binding.apply {
                    progressIndicator.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                    noUsers.visibility = View.GONE
                }
                Toast.makeText(this, resources.getText(R.string.server_error), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun isOnline(): Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                return true
            }
        }
        return false
    }
}