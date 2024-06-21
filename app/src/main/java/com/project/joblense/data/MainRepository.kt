package com.project.joblense.data

import com.project.joblense.data.local.UserPreference
import com.project.joblense.data.model.User
import com.project.joblense.data.network.auth.ApiService
import com.project.joblense.data.network.request.LoginRequest
import com.project.joblense.data.network.request.RegisterRequest
import com.project.joblense.data.network.response.LoginResponse
import com.project.joblense.data.network.response.RegisterResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class MainRepository(
    private val apiService: ApiService,
    private val userPreference: UserPreference,
) {

    suspend fun saveSession(user: User) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<User> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }


    suspend fun register(name: String, username: String,password: String): Response<RegisterResponse> {
        val registerRequest = RegisterRequest(name,username,password)
        return apiService.register(registerRequest)
    }



    suspend fun login(username: String, password: String): Response<LoginResponse> {
        val loginRequest = LoginRequest(username, password)
        return apiService.login(loginRequest)
    }


    companion object {
        fun getInstance(
            apiService: ApiService,
            userPreference: UserPreference,

            ): MainRepository =
            MainRepository(apiService, userPreference)
    }

}