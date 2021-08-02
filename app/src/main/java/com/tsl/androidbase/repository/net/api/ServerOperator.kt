package com.tsl.androidbase.repository.net.api

import com.tsl.androidbase.repository.net.throwable.ApiException
import com.tsl.androidbase.repository.net.throwable.ThrowableTransformer
import com.google.gson.Gson
import com.jowney.common.net.RetrofitMaster
import com.jowney.common.util.Md5Tool
import com.jowney.common.util.rxjava.SchedulerTransformer
import io.reactivex.disposables.CompositeDisposable
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

/// operatorFunction 功能控制类接口
/// operatorCheck 检查类接口
/// operatorData 获取数据类接口
object ServerOperator {
    //界面相关的网络操作将其disposable添加到该集合中，当退出界面时，断开连接
    private var mCompositeDisposable = CompositeDisposable()

    private fun operate(): ServerApi {
        return RetrofitMaster.getInstance().serverApi as ServerApi
    }

    fun clear() = this.mCompositeDisposable.clear()

    fun operatorFuncLogin() {
        var map: HashMap<String, String> = HashMap();
        map["pass word"] = Md5Tool.md5_32Encrypt("admin@tsl888");
        map["username"] = "admin";
        var json: String = Gson().toJson(map);
        val requestBody: RequestBody =
            json.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        val disposable = operate().apiFunctionLogin(requestBody)
            .compose(SchedulerTransformer())
            .subscribe({
            },
                {
                })


        mCompositeDisposable.add(disposable);
    }

    fun operatorDataJoke() {
        val disposable = operate().apiDataJoke()
            .compose(SchedulerTransformer())
            .compose(ThrowableTransformer())
            .subscribe({

            }, {
                var exeption: ApiException = it as ApiException
                //本地打印日志搞一下
                //网络监控crash搞一下
                //leakcanary
            })
    }

}