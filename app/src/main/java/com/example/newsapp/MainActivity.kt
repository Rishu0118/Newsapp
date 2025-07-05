package com.example.newsapp

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.newsapp.data.ApiBuilder.ApiBuilder
import com.example.newsapp.presentation.NewsAppViewModel
import com.example.newsapp.presentation.navgation.Routes.AppNavigation
import com.example.newsapp.ui.theme.NewsappTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel by  viewModels<NewsAppViewModel>()
            NewsappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   Box(modifier = Modifier.fillMaxSize().padding(innerPadding)){

                       AppNavigation(modifier = Modifier,viewModel = viewModel)

                   }
                }
                  GlobalScope.launch(Dispatchers.Unconfined) {
                    Log.d("ApiResponse", "onCreate:${  ApiBuilder.retrofitObject().getHeadlines()
                    } ")
                }
            }
        }
    }
}





