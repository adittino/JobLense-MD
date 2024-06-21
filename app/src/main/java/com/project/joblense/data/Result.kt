package com.project.joblense.data

data class Result<T>(
    val success: Boolean,
    val data: T? = null,
    val error: String? = null
)