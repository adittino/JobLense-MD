package com.project.joblense.data.network.recommendation

import com.project.joblense.data.network.auth.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiConfigRecommendation {

    companion object {

        var base_url = "https://job-recommendation-api-4fh4vc7vdq-et.a.run.app/"

        fun getApiService(token: String): RecommendationService {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val authInterceptor = Interceptor { chain ->
                val req = chain.request()
                val requestHeaders =
                    req.newBuilder().addHeader("Authorization", "Bearer $token").build()
                chain.proceed(requestHeaders)
            }
            val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor)
                .addInterceptor(authInterceptor)

                .build()
            val retrofit = Retrofit.Builder().baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create()).client(client).build()
            return retrofit.create(RecommendationService::class.java)
        }


    }
}