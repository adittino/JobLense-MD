package com.project.joblense.data.network.auth

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {

        var base_url = "https://us-central1-joblens-capstone.cloudfunctions.net/auth-api/"

        fun getApiService(token: String): ApiService {
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
            return retrofit.create(ApiService::class.java)
        }


    }
}