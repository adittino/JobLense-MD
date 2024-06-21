package com.project.joblense.data.di

import android.content.Context
import com.project.joblense.data.MainRepository
import com.project.joblense.data.RecommendRepository
import com.project.joblense.data.local.UserPreference
import com.project.joblense.data.local.dataStore
import com.project.joblense.data.network.auth.ApiConfig
import com.project.joblense.data.network.recommendation.ApiConfigRecommendation
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object RecommendInjection {

    fun provideRepository(context: Context): RecommendRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }

        // val database = StoryDatabase.getDatabase(context)

        val apiService = ApiConfigRecommendation.getApiService(user.token)
        return RecommendRepository.getInstance(apiService, pref)


    }

}