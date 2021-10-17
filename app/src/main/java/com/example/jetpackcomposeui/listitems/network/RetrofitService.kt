package com.example.jetpackcomposeui.listitems.network

import com.example.jetpackcomposeui.listitems.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("users")
    suspend fun getUserList(@Query("page") page: Int) : UserResponse
}