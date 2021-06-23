package com.example.androidbase

import android.content.Context
import com.example.androidbase.repository.net.api.ServerApi
import com.example.androidbase.repository.net.api.ServerURL
import com.jowney.common.BaseApplication
import com.jowney.common.net.RetrofitMaster

class APP : BaseApplication() {
    companion object {
        var mContext: Context? = null;
    }

    override fun onCreate() {
        super.onCreate()
        mContext = this;
        RetrofitMaster.getInstance().init(ServerURL.BASE_URL,ServerApi::class.java,null)
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
    }
}