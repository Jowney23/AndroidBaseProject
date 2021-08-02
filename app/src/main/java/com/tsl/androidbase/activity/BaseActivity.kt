package com.tsl.androidbase.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    var isFirstPress = true
    var mLastPressTime: Long = 0 //上次按键时间
    var mGoBackThreshold: Long = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBar()
    }

    abstract fun setStatusBar();

    override fun onBackPressed() {
        super.onBackPressed()
        //如果当前activity是根activity则阻拦返回
       /* if (isTaskRoot) {
            super.onBackPressed()
            ToastUtils.show("根Activity")
        }
        if (isFirstPress) {
            isFirstPress = false
            mLastPressTime = System.currentTimeMillis()
        } else {
            isFirstPress = true
            if ((System.currentTimeMillis() - mLastPressTime) < mGoBackThreshold) super.onBackPressed()
            else ToastUtils.show("WO! 快速点击两次退出")
        }*/
    }
}