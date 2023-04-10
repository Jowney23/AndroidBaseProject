package com.tsl.androidbase.activity.login

import android.os.Bundle
import android.util.Log
import androidx.navigation.Navigation
import com.jowney.player.player.AndroidMediaPlayer
import com.jowney.player.ui.StandardVideoController
import com.jowney.player.ui.VideoView
import com.tsl.androidbase.R
import com.tsl.androidbase.activity.BaseActivity


class LoginActivity : BaseActivity() {
    companion object {
        var TAG = "JLogin"
    }

    lateinit var mVideoView: VideoView<AndroidMediaPlayer>
    private var mLocalPathList = listOf("file:///mnt/sdcard/tslpp/1.mp4","file:///mnt/sdcard/tslpp/2.mp4","file:///mnt/sdcard/tslpp/3.mp4")
    lateinit var mStandardVideoController: StandardVideoController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mVideoView = findViewById(R.id.videoview)
        mStandardVideoController = StandardVideoController(this)
        mStandardVideoController.addDefaultControlComponent("TSL", false)
        mVideoView.setVideoController(mStandardVideoController)
        mVideoView.setUrl("file:///mnt/sdcard/tslpp/3.mp4")
        mVideoView.start()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onReStart")
    }

    override fun onStart() {
        super.onStart()
        mVideoView.setOnStateChangeListener(object : VideoView.SimpleOnStateChangeListener() {
            private var mCurrentVideoPosition = 0

            override fun onPlayStateChanged(playState: Int) {
                if (playState == VideoView.STATE_PLAYBACK_COMPLETED) {

                    mCurrentVideoPosition++
                    if (mCurrentVideoPosition >= mLocalPathList.size) mCurrentVideoPosition = 0
                    //重新设置数据
                    mVideoView.setUrl(mLocalPathList[mCurrentVideoPosition])
                    //开始播放
                    mVideoView.replay(true)
                }
            }

        })
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
        this.finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        mVideoView.release()
        Log.d(TAG, "onDestroy")
    }

    override fun setStatusBar() {

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        Log.d(TAG, "是否获取焦点$hasFocus")
    }

    override fun onBackPressed() {
        Log.d("Jowney", this.supportFragmentManager.backStackEntryCount.toString())
        super.onBackPressed()
    }
}