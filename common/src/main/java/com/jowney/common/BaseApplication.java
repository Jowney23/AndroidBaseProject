package com.jowney.common;

import android.app.Application;

import com.hjq.permissions.XXPermissions;
import com.hjq.toast.ToastUtils;
import com.hjq.toast.style.WhiteToastStyle;
import com.jowney.common.net.RetrofitMaster;
import com.jowney.common.util.PermissionInterceptor;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化吐司工具类
        ToastUtils.init(this, new WhiteToastStyle());

        // 设置权限申请拦截器
        XXPermissions.setInterceptor(new PermissionInterceptor());
    }
}
