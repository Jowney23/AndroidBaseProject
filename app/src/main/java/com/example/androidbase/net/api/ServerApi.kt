package com.example.androidbase.net.api


import com.google.gson.JsonObject
import com.jowney.common.net.RetrofitMaster
import io.reactivex.Observable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface ServerApi {

    @POST("rights/v1/dologin_app")
    fun login(@Body body: RequestBody): Observable<ResponseBody>
}
