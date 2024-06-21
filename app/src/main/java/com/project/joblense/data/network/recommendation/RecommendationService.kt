package com.project.joblense.data.network.recommendation

import com.project.joblense.data.network.request.RecommendRequest
import com.project.joblense.data.network.response.HistoryResponse
import com.project.joblense.data.network.response.RecommendResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RecommendationService {

    @POST("recommend-jobs")
    suspend fun getRecommend(
        @Body recommendRequest: RecommendRequest
    ): Response<RecommendResponse>

    @GET("recommendation-history")
    suspend fun getHistory(): Response<HistoryResponse>

}