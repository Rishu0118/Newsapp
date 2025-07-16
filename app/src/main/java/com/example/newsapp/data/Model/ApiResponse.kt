package com.example.newsapp.data.Model
data class ApiResponse(val articles: List<Article>,
                       val status: String,
                       val totalResults: Int)