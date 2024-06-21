package com.project.joblense.data

import com.project.joblense.data.local.UserPreference
import com.project.joblense.data.network.recommendation.RecommendationService
import com.project.joblense.data.network.request.RecommendRequest
import com.project.joblense.data.network.response.HistoryResponse
import com.project.joblense.data.network.response.RecommendResponse
import retrofit2.Response

class RecommendRepository(
    private val apiService: RecommendationService,
    private val userPreference: UserPreference,
) {

    suspend fun getRecommend(skills: String): Response<RecommendResponse> {
        val recommendRequest = RecommendRequest(skills)
        return apiService.getRecommend(recommendRequest)
    }

    suspend fun getHistory() : Response<HistoryResponse> {
        return apiService.getHistory()
    }

    companion object {
        fun getInstance(
            apiService: RecommendationService,
            userPreference: UserPreference,

            ): RecommendRepository =
            RecommendRepository(apiService, userPreference)
    }
}