package com.example.newsapp.presentation.navgation.Routes

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.newsapp.data.Model.Article
import com.example.newsapp.presentation.NewsAppViewModel
import com.example.newsapp.presentation.screens.CatogryDetailScreenUI
import com.example.newsapp.presentation.screens.HomeScreenUI

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable

fun AppNavigation(modifier: Modifier = Modifier,viewModel: NewsAppViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HomeScreen) {
    composable<HomeScreen>{
        HomeScreenUI(navController = navController,viewModel = viewModel)
    }
    composable<CategoryScreen>{
            val CatogryScreen=it.toRoute<CategoryScreen>()
            var article = Article(
                author = CatogryScreen.author,
                content = CatogryScreen.content,
                description = CatogryScreen.description,
                publishedAt = CatogryScreen.publishedAt,
                title = CatogryScreen.title,
                url = CatogryScreen.url,
                urlToImage = CatogryScreen.urlToImage,
                name = CatogryScreen.source?.name.toString(),
                id = CatogryScreen.source?.id.toString()
            )
            CatogryDetailScreenUI(article = article, modifier = modifier)
        }

    }

}


