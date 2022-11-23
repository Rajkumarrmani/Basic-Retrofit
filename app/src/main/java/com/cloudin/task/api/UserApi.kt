package com.cloudin.task.api

import com.cloudin.task.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {

    @GET("api/?results=20")
    suspend fun getUserDetail(): Response<User>
}