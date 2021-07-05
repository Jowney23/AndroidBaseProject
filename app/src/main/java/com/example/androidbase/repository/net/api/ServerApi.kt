package com.example.androidbase.repository.net.api


import com.example.androidbase.repository.net.bean.JokeData
import com.example.androidbase.repository.net.bean.ServerResponse
import io.reactivex.Observable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*
import java.util.*

/// apiFunction 功能控制类接口
/// apiCheck 检查类接口
/// apiData 获取数据类接口
interface ServerApi {

    @POST("rights/v1/dologin_app")
    fun apiFunctionLogin(@Body body: RequestBody): Observable<ResponseBody>


/*
     //动态添加参数
    @GET(ServerURL.URL_DATA_JOKE)
     fun apiDataJoke(@QueryMap map: Map<String, String>)
*/

    @GET("${ServerURL.URL_DATA_JOKE}?page=1&pagesize=20&key=9b168e750a5301ea758c05b81f62b91")
    fun apiDataJoke():Observable<ServerResponse<JokeData>>
}
