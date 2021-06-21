package com.example.androidbase

import android.app.Application
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.view.Window
import com.example.androidbase.net.api.ServerApi
import com.example.androidbase.net.api.ServerURL
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