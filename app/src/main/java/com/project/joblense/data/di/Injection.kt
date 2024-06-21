package com.project.joblense.data.di

import android.content.Context
import com.project.joblense.data.MainRepository
import com.project.joblense.data.local.UserPreference
import com.project.joblense.data.local.dataStore
import com.project.joblense.data.network.auth.ApiConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {

    fun provideRepository(context: Context): MainRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }

        // val database = StoryDatabase.getDatabase(context)

        val apiService = ApiConfig.getApiService(user.token)
        return MainRepository.getInstance(apiService, pref)


    }

}