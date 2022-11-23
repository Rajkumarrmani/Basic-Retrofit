package com.cloudin.task.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.cloudin.task.api.RetrofitInstance
import com.cloudin.task.data.repository.DefaultRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel : ViewModel() {

    private val repository = DefaultRepository(RetrofitInstance.api)

    var userData = liveData(Dispatchers.IO) {
        val result = repository.getUserData()
        emit(result)
    }


}