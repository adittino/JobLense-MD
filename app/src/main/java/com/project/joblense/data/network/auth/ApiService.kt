package com.project.joblense.data.network.auth

import com.project.joblense.data.network.request.LoginRequest
import com.project.joblense.data.network.request.RegisterRequest
import com.project.joblense.data.network.response.LoginResponse
import com.project.joblense.data.network.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): Response<RegisterResponse>


    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

}