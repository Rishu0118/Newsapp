package com.example.newsapp.data

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.newsapp.data.ApiBuilder.ApiBuilder
import com.example.newsapp.data.ApiState
import com.example.newsapp.data.Model.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


//     CALLING ME KYA KYA PRBLM H VO BTATA H  AGR LODING ME KYA SHOW KRE
//ERROR PR KYA KRE NET PRBLM PR KYA KRE HVO BTATA H
class NewRepo {
     val apiInstance = ApiBuilder.retrofitObject()
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
   suspend fun getHeadLine(country:String="us"):Flow<ApiState> {
        return flow {
            emit(ApiState(loading = true))
            try {
                val response = apiInstance.getHeadlines(country = country)
                emit(ApiState(data = response))
            } catch (e: HttpException) {
                emit(ApiState(error = e.localizedMessage))
            } catch (e: Exception) {
                emit(ApiState(error = e.localizedMessage))
            }
        }

    }
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
suspend fun getEverything(q:String):Flow<ApiState>{
    return flow{
        emit(ApiState(loading = true))
        try{
            val response = apiInstance.getEverything(q=q)
            emit(ApiState(data = response))
        }catch (e:HttpException){
            emit(ApiState(error = e.localizedMessage))
        }
        catch (e:Exception){
            emit(ApiState(error = e.localizedMessage))
        }
    }
}




}
data class ApiState(
    var loading : Boolean? = false,
    var error : String? = "",
    var  data : ApiResponse?=null
)