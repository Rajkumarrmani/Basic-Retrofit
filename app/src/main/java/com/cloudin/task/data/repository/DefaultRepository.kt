package com.cloudin.task.data.repository

import com.cloudin.task.api.UserApi

class DefaultRepository(private val retrofitService: UserApi) {
    suspend fun getUserData() = retrofitService.getUserDetail()
}