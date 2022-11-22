package com.cloudin.task.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cloudin.task.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setUpObservers()

    }

    private fun setUpObservers() {
        viewModel.userData2.observe(this) { data ->
            Timber.tag(TAG).d("userData : ${data.body()}")
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}