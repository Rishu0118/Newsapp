package com.example.newsapp.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.newsapp.data.Model.Article
import com.example.newsapp.data.Model.Source

@Preview(showBackground = true, showSystemUi = true)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable

fun CatogryDetailScreenUI(
    modifier: Modifier = Modifier,
    article: Article = Article(
    author = "ET Online",
    content = "Elon Musk, who spent years building his reputation as a tech mogul and business visionary, is now facing a decline in public favor, according to a new poll by The Associated Press-NORC Center for Pub… [+2325 chars]",
    description = "Elon Musk's public image has taken a hit, with favorability dropping from 41% to 33% according to a recent poll. His involvement in government downsizing efforts, particularly through the Department of Government Efficiency, has drawn criticism, with many bel…",
    publishedAt = "2025-04-28T05:32:42Z",
    source = Source(
        id = "the-times-of-india",
        name = "The Times of India"
    ),
    title = "Who loves Elon Musk? Tesla founder’s popularity plummets in U.S., poll shows just a third view him favorably",
    url = "https://economictimes.indiatimes.com/news/international/global-trends/who-loves-elon-musk-tesla-founders-popularity-plummets-in-u-s-poll-shows-just-a-third-view-him-favorably/articleshow/120686182.cms",
    urlToImage = "https://img.etimg.com/thumb/msid-120686561,width-1200,height-630,imgsize-576732,overlay-economictimes/articleshow.jpg",
    name = article.source?.name.toString(),
    id = article.source?.id.toString()
)){
   Scaffold (modifier=modifier.fillMaxSize(),
       topBar = {
           TopAppBar(title = {
               Text(text = "News App")
           }
           )
       }
   ){
       Column (
           modifier=modifier.fillMaxSize().padding(it),
           horizontalAlignment = Alignment.CenterHorizontally
       ){
           Spacer(modifier=modifier.height(24.dp))
           Text(text= article.title.toString(),
            fontStyle = FontStyle.Italic, style = TextStyle(
                   fontSize = MaterialTheme.typography.titleLarge.fontSize,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                letterSpacing = 2.sp,
                lineHeight = 25.sp
               )
           )
           Spacer(modifier=modifier.height(24.dp))
           AsyncImage(model = article.urlToImage,
               contentDescription = null, modifier=modifier.size(300.dp))

       }
   }
}

