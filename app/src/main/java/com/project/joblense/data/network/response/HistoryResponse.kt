package com.project.joblense.data.network.response

import com.google.gson.annotations.SerializedName


data class HistoryResponse (
    val history: List<History>
)


data class History (
    @SerializedName("recommended_jobs")
    val recommendedJobs: List<String>,

    @SerializedName("skills")
    val skills: String,

    @SerializedName("timestamp")
    val timestamp: String
)