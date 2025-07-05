package com.example.newsapp.presentation.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.newsapp.R
import com.example.newsapp.presentation.NewsAppViewModel
import com.example.newsapp.presentation.navgation.Routes.CategoryScreen

@SuppressLint("SuspiciousIndentation", "MutableCollectionMutableState")
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenUI(modifier: Modifier = Modifier,navController: NavController,viewModel: NewsAppViewModel){
   val searchTerm= rememberSaveable { mutableStateOf("") }
    val state = viewModel.state.collectAsState()
    val catogryToSearch = rememberSaveable {
       mutableStateOf( arrayListOf("health", "business", "entertainment", "general", "science", "sports", "technology"))
    }

    if(state.value.loading==true){
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.fillMaxSize()

        ){
            CircularProgressIndicator()
        }
    }else if(state.value.error.isNullOrBlank().not()){
        Text(text=state.value.error.toString())
    }
else{
        Column(modifier=modifier.fillMaxSize()) {
            Row{
                OutlinedTextField(value = searchTerm.value, onValueChange = {
                    searchTerm.value=it
                }, placeholder = {Text(text = "Search")}, label = {Text(text = "Search")})
                IconButton(onClick ={
                    viewModel.getEverything(q = searchTerm.value)

                } , enabled = searchTerm.value.isNullOrBlank().not()) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
            }
            Spacer(modifier=modifier.padding(8.dp).height(20.dp))
            LazyRow (modifier=modifier.fillMaxWidth()){
                items(catogryToSearch.value.size) {
                    Card(modifier=modifier.padding(8.dp).clickable{
                        viewModel.getEverything(q=it.toString())
                    }){Text(text=catogryToSearch.value[it], fontSize = 20.sp,)}
                }
            }

            val data=state.value.data
            if(data?.articles!!.isEmpty()) {
                Text(text="no data found")
            }

            else {
                val articles=data.articles.filter { article ->
                    article.title?.contains("Removed") != true
                }
                LazyColumn (modifier=modifier.fillMaxSize()){

                    items(articles.size){ article->
                        Card(
                            modifier=modifier.fillMaxWidth().wrapContentHeight().padding(8.dp).
                            clickable{navController.navigate(CategoryScreen(
                                author = articles[article].author,
                                content = articles[article].content,
                                description = articles[article].description,
                                publishedAt = articles[article].publishedAt,
                                source = articles[article].source,
                                id = articles[article].url,
                                name = articles[article].urlToImage,
                                title = articles[article].title,
                                url = articles[article].url,
                                urlToImage = articles[article].urlToImage
                            ))}
                        ){
                            Column {
                               if(articles[article].urlToImage.isNullOrBlank()){
                                   Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = null)
                               }
                                else{
                                   AsyncImage(
                                       model = articles[article].urlToImage,
                                       contentDescription = null,
                                       placeholder = painterResource(R.drawable.ic_launcher_foreground)

                                   )
                                }

                                Text(text = articles[article].title.toString())
                                Text(text = articles[article].description.toString())
                                Text(text = articles[article].author.toString())
                                Text(text = articles[article].publishedAt.toString())

                            }

                        }
                    }
                }
            }
        }
}

}




