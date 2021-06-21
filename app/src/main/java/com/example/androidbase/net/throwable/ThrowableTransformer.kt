package com.example.androidbase.net.throwable

import com.example.androidbase.APP
import com.example.androidbase.R
import com.example.androidbase.net.bean.Response
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.functions.Function

class ThrowableTransformer<T> : ObservableTransformer<Response<T>, T> {
    override fun apply(upstream: Observable<Response<T>>): ObservableSource<T> {
        return upstream.onErrorResumeNext(Function {
                return@Function Observable.error(ExceptionHandler.handle(it))
            })
            .flatMap(Function {
                val code: Int = it.code
                val message = it.message ?: APP.mContext?.getString(R.string.response_message_null);
                if (code == 0) {
                    if (it.data == null) {
                        return@Function Observable.error(
                            ApiException(code, APP.mContext?.getString(R.string.response_data_null))
                        )
                    } else {
                        return@Function Observable.just(it.data)
                    }
                }
                return@Function Observable.error(ApiException(code, message))
            })
    }
}


