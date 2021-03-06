package com.jowney.common;

import android.app.Application;
import android.content.Context;

import com.hjq.permissions.XXPermissions;
import com.hjq.toast.ToastUtils;
import com.hjq.toast.style.WhiteToastStyle;
import com.jowney.common.net.RetrofitMaster;
import com.jowney.common.util.PermissionInterceptor;
import com.tencent.bugly.crashreport.CrashReport;

public class BaseApplication extends Application {
   public static Context application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        // 初始化吐司工具类
        ToastUtils.init(this, new WhiteToastStyle());

        // 设置权限申请拦截器
        XXPermissions.setInterceptor(new PermissionInterceptor());

        //腾讯Bugly初始化
        CrashReport.initCrashReport(getApplicationContext(), "86eb19a6cd", BuildConfig.DEBUG);
    }
}
