package com.example.newsapp.data.ApiService

import com.example.newsapp.data.Model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
  suspend  fun getHeadlines(
        @Query("country") country: String="us",
        @Query("apiKey") apiKey: String="67ddf02686d34e779c3707c106dd7add"
    ):  ApiResponse

  @GET("everything")
  suspend fun getEverything(
      @Query("q") q: String="us",
      @Query("apiKey") apiKey: String="67ddf02686d34e779c3707c106dd7add"

  ): ApiResponse


}