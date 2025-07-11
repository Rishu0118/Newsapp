package com.example.newsapp.presentation

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.ApiBuilder.ApiBuilder
import com.example.newsapp.data.Model.ApiResponse
import com.example.newsapp.data.NewRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class NewsAppViewModel : ViewModel() {
val repo = NewRepo()
    private val _state = MutableStateFlow(AppState())
    val state = _state.asStateFlow()

    init {
        getHeadLine()
    }
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getHeadLine(country:String="us"){
        viewModelScope.launch {
            repo.getHeadLine().collectLatest {
                if (it.loading == true) {
                    _state.value = AppState(loading = true)

                } else if (it.error.isNullOrBlank().not()) {
                    _state.value = AppState(error = it.error)
                } else {
                    _state.value = AppState(data = it.data, loading = false)
                }
            }

            }
        }
    fun getEverything(q:String="us"){
        viewModelScope.launch {
            repo.getEverything(q).collectLatest {
                if (it.loading == true) {
                    _state.value = AppState(loading = true)

                } else if (it.error.isNullOrBlank().not()) {
                    _state.value = AppState(error = it.error)
                } else {
                    _state.value = AppState(data = it.data, loading = false)
                }
            }

        }
    }
    }






data class AppState(
    var loading : Boolean? = false,
    var error : String? = "",
    var  data : ApiResponse?=null
)