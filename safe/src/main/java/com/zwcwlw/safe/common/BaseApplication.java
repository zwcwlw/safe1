package com.zwcwlw.safe.common;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by Administrator on 2015/10/29.
 *
 * 定义整个app声明周期范围内的一些全局变量，方便在任何地方都可以使用这些对象
 */
public class BaseApplication extends Application {

    public static Context context = null;

    public static Handler handler = null;

    public static Thread mainThread = null;

    public static int mainThreadId=0;

    @Override
    public void onCreate() {
        super.onCreate();
        //当前程序执行的线程
        mainThread = Thread.currentThread();
        //当前程序执行的线程id
        mainThreadId = android.os.Process.myTid();
        //全局context上下文
        context = getApplicationContext();
        //handler对象
        handler = new Handler();
        //注册自定义的全部捕获异常
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(this);
    }
}
