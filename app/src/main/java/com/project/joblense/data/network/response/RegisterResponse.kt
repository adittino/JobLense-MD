package com.project.joblense.data.network.response


data class RegisterResponse (
    val status: String,
    val message: String,
    val data: Register
)


data class Register (
    val id: String,
    val email: String,
    val password: String,
    val name: String
)