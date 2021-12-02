package com.tsl.androidbase

import android.content.Context
import com.jowney.common.BaseApplication
import com.jowney.common.net.RetrofitMaster
import com.jowney.common.util.logger.*
import com.jowney.player.player.AndroidMediaPlayerFactory
import com.jowney.player.player.VideoViewConfig
import com.jowney.player.player.VideoViewManager
import com.tsl.androidbase.repository.db.Db
import com.tsl.androidbase.repository.net.api.ServerApi
import com.tsl.androidbase.repository.net.api.ServerURL


class APP : BaseApplication() {
    companion object {
        lateinit var mContext: Context
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
        //视频模块儿
        //播放器配置，注意：此为全局配置，按需开启
        //播放器配置，注意：此为全局配置，按需开启
        VideoViewManager.setConfig(
            VideoViewConfig.newBuilder()
                .setLogEnabled(BuildConfig.DEBUG) //调试的时候请打开日志，方便排错
                .setPlayerFactory(AndroidMediaPlayerFactory.create()) //                .setPlayerFactory(AndroidMediaPlayerFactory.create()) //不推荐使用，兼容性较差
                // 设置自己的渲染view，内部默认TextureView实现
                //                .setRenderViewFactory(SurfaceRenderViewFactory.create())
                // 根据手机重力感应自动切换横竖屏，默认false
                //                .setEnableOrientation(true)
                // 监听系统中其他播放器是否获取音频焦点，实现不与其他播放器同时播放的效果，默认true
                //                .setEnableAudioFocus(false)
                // 视频画面缩放模式，默认按视频宽高比居中显示在VideoView中
                //                .setScreenScaleType(VideoView.SCREEN_SCALE_MATCH_PARENT)
                // 适配刘海屏，默认true
                //                .setAdaptCutout(false)
                // 移动网络下提示用户会产生流量费用，默认不提示，
                // 如果要提示则设置成false并在控制器中监听STATE_START_ABORT状态，实现相关界面，具体可以参考PrepareView的实现
                //                .setPlayOnMobileNetwork(false)
                // 进度管理器，继承ProgressManager，实现自己的管理逻辑
                //                .setProgressManager(new ProgressManagerImpl())
                .build()
        )
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
    }
}