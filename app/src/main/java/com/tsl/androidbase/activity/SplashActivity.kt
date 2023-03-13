package com.tsl.androidbase.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.hjq.toast.ToastUtils
import com.jaeger.library.StatusBarUtil
import com.jowney.common.sample.CommonRefreshActivity
import com.jowney.common.util.logger.L
import com.jowney.common.util.logger.L2
import com.tsl.androidbase.R
import com.tsl.androidbase.activity.bottomnavication.BottomNavicationActivity
import com.tsl.androidbase.activity.login.LoginActivity
import com.tsl.androidbase.activity.show.ShowActivity

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        XXPermissions.with(this) // 不适配 Android 11 可以这样写
            .permission(Permission.MANAGE_EXTERNAL_STORAGE,Permission.CAMERA)
            .request(OnPermissionCallback { permissions, all ->
                if (all) {
                    ToastUtils.show("获取所需权限成功")
                }
            })
    }

    override fun onResume() {
        super.onResume()
        L.v("我是启动页面哦！")
       // startActivity(Intent(this,BottomNavicationActivity::class.java))
    }
    override fun setStatusBar() {
        StatusBarUtil.setTransparent(this)
    }
}