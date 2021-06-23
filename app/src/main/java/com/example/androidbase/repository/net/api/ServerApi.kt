package com.example.androidbase.repository.net.api


import io.reactivex.Observable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*


interface ServerApi {

    @POST("rights/v1/dologin_app")
    fun login(@Body body: RequestBody): Observable<ResponseBody>
}
