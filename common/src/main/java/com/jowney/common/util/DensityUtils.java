package com.jowney.common.util;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.Log;

import com.jowney.common.util.logger.L;

public class DensityUtils {


    // px值 = dp值 x (手机dpi / 标准dpi即160) = dp值 x displayMetrics.density
    private static float appDensity;
    // px值 = sp值 x displayMetrics.scaledDensity (这个主要是针对字体Sp这个单位的转换)
    private static float appScaleDensity;
    //屏幕像素密度
    private static float appDensityDpi;


    private DensityUtils() {
        throw new UnsupportedOperationException("you can't instantiate EDensityUtils...");
    }


    public static void setDensity(final Application application, Activity activity) {
        //获取当前app的屏幕显示信息
        DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
        if (appDensity == 0) {
            //初始化赋值操作
            appDensity = displayMetrics.density;
            appScaleDensity = displayMetrics.scaledDensity;
            appDensityDpi = displayMetrics.densityDpi;
            Log.d("DensityUtils","修改前数据--->appDesity:"+appDensity +"  appScaleDensity:"+appScaleDensity +"  dpi:"+ displayMetrics.densityDpi);

            //添加字体变化监听回调
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    //字体发生更改，重新对scaleDensity进行赋值
                    if (newConfig != null && newConfig.fontScale > 0) {
                        appScaleDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }
        float targetDensity = 0;
        //计算目标值density, scaleDensity, densityDpi
        if (appDensityDpi > 0 && appDensityDpi <= 120)
            targetDensity = 0.75F;
        if (appDensityDpi > 120 && appDensityDpi <= 160)
            targetDensity = 1F;
        if (appDensityDpi>160 && appDensityDpi<=240)
            targetDensity = 1.5F;
        if (appDensityDpi>240 && appDensityDpi<=320)
            targetDensity = 2F;
        if (appDensityDpi>320 )
            targetDensity = 3F;
        float targetScaleDensity = targetDensity;
        int targetDensityDpi = (int) (targetDensity * 160);

        //替换Activity的density, scaleDensity, densityDpi
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        dm.density = targetDensity;
        dm.scaledDensity = targetScaleDensity;
        dm.densityDpi = targetDensityDpi;
        Log.d("DensityUtils","修改后数据---->appDesity:"+  dm.density  +"  appScaleDensity:"+ dm.scaledDensity +"  dpi:"+    dm.densityDpi);

    }

}