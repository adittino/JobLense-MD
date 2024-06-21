package com.project.joblense.data.network.response

import com.google.gson.annotations.SerializedName


data class RecommendResponse (
    @SerializedName("recommended_jobs")
    val recommendedJobs: List<String>? = null
)