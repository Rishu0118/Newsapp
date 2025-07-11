package com.example.newsapp.data.ApiBuilder

import com.example.newsapp.data.ApiService.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiBuilder {
    fun retrofitObject(): ApiService {
        return Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .baseUrl("https://newsapi.org/v2/ ")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }
}

