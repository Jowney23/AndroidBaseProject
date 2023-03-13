package com.jowney.common.server;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import java.io.IOException;

public class HttpService extends Service {

    private HttpServer httpServer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        httpServer = new HttpServer();
        try {
            httpServer.start(30000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        if (httpServer != null) {
            httpServer.stop();
        }
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }

    /**
     * 启动服务
     *
     * @param context
     */
    public static final void startService(Context context) {
        Intent intent = new Intent(context,
                HttpService.class);
        context.startService(intent);
    }


    /**
     * 关闭服务
     */
    public static void stopService(Context context) {
        Intent intent = new Intent(context, HttpService.class);
        context.stopService(intent);
    }
}