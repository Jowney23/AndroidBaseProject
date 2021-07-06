package com.example.androidbase

import android.content.Context
import com.example.androidbase.repository.net.api.ServerApi
import com.example.androidbase.repository.net.api.ServerURL
import com.jowney.common.BaseApplication
import com.jowney.common.net.RetrofitMaster
import com.jowney.common.util.logger.*


class APP : BaseApplication() {
    companion object {
        var mContext: Context? = null;
    }

    override fun onCreate() {
        super.onCreate()
        mContext = this;
        //日志模块儿初始化
        L.addLogAdapter(object : AndroidLogAdapter(
            PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(2)         // (Optional) How many method line to show. Default 2
                .methodOffset(0)        // (Optional) Hides internal method calls up to offset. Default 5
                .tag("My custom tag")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build()) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
        L.addLogAdapter(object : DiskLogAdapter(
            CsvFormatStrategy.newBuilder()
            .tag("jowney")
            .build()){
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return !BuildConfig.DEBUG
            }
        })
        //网络模块儿初始化
        RetrofitMaster.getInstance().init(ServerURL.URL_BASE, ServerApi::class.java, null)
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
    }
}