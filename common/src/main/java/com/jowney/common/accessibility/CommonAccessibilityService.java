package com.jowney.common.accessibility;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Intent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.jowney.common.util.logger.L;

import java.util.List;

public class CommonAccessibilityService extends AccessibilityService {
    @Override
    public void onCreate() {
        super.onCreate();
        L.v("我启动了");
    }


    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        L.v("我连接了");
        //   performGlobalAction(GLOBAL_ACTION_TAKE_SCREENSHOT);

    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
      //  L.v(accessibilityEvent.toString());
        AccessibilityNodeInfo nodeInfo = accessibilityEvent.getSource();
       /* AccessibilityNodeInfo info = getRootInActiveWindow();
        L.v(info.toString());*/
        List<AccessibilityNodeInfo> dd = nodeInfo.findAccessibilityNodeInfosByText("签到");
        L.v(dd.toString());
    }

    @Override
    public void onInterrupt() {

    }
}
