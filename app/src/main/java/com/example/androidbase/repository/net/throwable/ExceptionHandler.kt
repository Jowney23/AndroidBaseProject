package com.example.androidbase.repository.net.throwable

import android.net.ParseException
import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ExceptionHandler {
    /**
     * 未知错误
     */
    const val UNKNOWN = 1000

    /**
     * 解析错误
     */
    const val PARSE_ERROR = 1001

    /**
     * 网络错误
     */
    const val NETWORK_ERROR = 1002

    /**
     * 协议错误
     */
    const val HTTP_ERROR = 1003

    fun handle(e: Throwable): ApiException {
        val ex: ApiException
        return if (e is JsonParseException
            || e is JSONException
            || e is ParseException
        ) {
            //解析错误
            ex = ApiException(PARSE_ERROR, e.message)
            ex
        } else if (e is HttpException) {
            ex = ApiException(HTTP_ERROR, e.message())
            ex
        } else if (e is ConnectException) {
            //网络错误
            ex = ApiException(NETWORK_ERROR, e.message)
            ex
        } else if (e is UnknownHostException || e is SocketTimeoutException) {
            //连接错误
            ex = ApiException(NETWORK_ERROR, e.message)
            ex
        } else {
            //未知错误
            ex = ApiException(UNKNOWN, e.message)
            ex
        }
    }
}