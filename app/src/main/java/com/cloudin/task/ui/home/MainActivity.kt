package com.cloudin.task.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cloudin.task.databinding.ActivityMainBinding
import com.cloudin.task.ui.adapter.UserAdapter
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var userAdapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.recyclerView.adapter = userAdapter


        setUpObservers()

    }

    private fun setUpObservers() {
        viewModel.userData2.observe(this) { data ->
            userAdapter.updateItems(data.body()?.results)
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}