package com.project.joblense.data.network.response

import com.google.gson.annotations.SerializedName


data class LoginResponse(
    val status: String,
    val message: String,
    val data: LoginData
)

data class LoginData(
    val user: LoginUser,
    val token: String,
    @SerializedName("expires_in")
    val expiresIn: Long
)

data class LoginUser(
    val password: String,
    val name: String,
    val id: String,
    val email: String
)
