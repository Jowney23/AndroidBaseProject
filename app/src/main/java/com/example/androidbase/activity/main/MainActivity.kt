package com.example.androidbase.activity.main

import android.content.Intent
import android.os.Bundle
import android.os.Debug
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.TranslateAnimation

import android.widget.LinearLayout

import com.example.androidbase.R
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MainActivity : BaseActivity() {
    companion object {
        var TAG = "MainActivity1"
    }

    var linearLayout: LinearLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        lifecycle.addObserver(MainPresenter())
        linearLayout = findViewById(R.id.id_ll_test)
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onResume() {
        super.onResume()

    }

    override fun setStatusBar() {
Executors.newSingleThreadExecutor()
    }

    fun onTestClickListener(view: View) {
        var translateAnimation: Animation =
            TranslateAnimation(0F, linearLayout?.width?.toFloat()!!, 0.0f, 0f)
        // 参数说明
        // fromXDelta ：视图在水平方向x 移动的起始值
        // toXDelta ：视图在水平方向x 移动的结束值
        // fromYDelta ：视图在竖直方向y 移动的起始值
        // toYDelta：视图在竖直方向y 移动的结束值

        // 步骤3：属性设置：方法名是在其属性前加“set”，如设置时长setDuration()
        translateAnimation.duration = 3000
        translateAnimation.setAnimationListener(object : AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
            }

            override fun onAnimationStart(animation: Animation?) {
            }
        })
        // 步骤4：播放动画
        linearLayout?.startAnimation(translateAnimation)
    }
}
