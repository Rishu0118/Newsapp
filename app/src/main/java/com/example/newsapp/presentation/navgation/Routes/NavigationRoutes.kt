package com.example.newsapp.presentation.navgation.Routes

import com.example.newsapp.data.Model.Source
import kotlinx.serialization.Serializable

@Serializable

    object HomeScreen
@Serializable

data class CategoryScreen(
    val author: String? = null,
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val source: Source? = null,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val id: String?=null,
    val name: String?=null
)


