package com.tsl.androidbase.repository.net.throwable

import com.tsl.androidbase.APP
import com.tsl.androidbase.R
import com.tsl.androidbase.repository.net.bean.ServerResponse
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.functions.Function

class ThrowableTransformer<T> : ObservableTransformer<ServerResponse<T>, T> {
    override fun apply(upstream: Observable<ServerResponse<T>>): ObservableSource<T> {
        return upstream
            .onErrorResumeNext(Function {
                return@Function Observable.error(ExceptionHandler.handle(it))
            }).flatMap(Function {
                val code: Int = it.error_code
                val message = it.reason ?: APP.mContext?.getString(R.string.response_message_null);
                if (code == 0) {
                    if (it.result == null) {
                        return@Function Observable.error(
                            ApiException(code, APP.mContext?.getString(R.string.response_data_null))
                        )
                    } else {
                        return@Function Observable.just(it.result)
                    }
                }
                return@Function Observable.error(ApiException(code, message))
            })
    }
}


