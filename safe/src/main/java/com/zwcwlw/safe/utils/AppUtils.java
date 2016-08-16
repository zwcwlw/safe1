package com.zwcwlw.safe.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * 作者：zwcwlw on 2016/7/26 21:02
 * 邮箱:zwcwlw@126.com
 */
public class AppUtils {
    private AppUtils() {
        /*cannot be instantiated 不能被实例化*/
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获取当前应用程序的版本号
     */
    public static String getVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            if (packageInfo == null) {
                packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "解析版本号失败";
        }

        return packageInfo.versionName;
    }

    /**
     * 获取当前应用程序的名称
     */
    public static String getAppName(Context context) throws PackageManager.NameNotFoundException {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        int labelRes = packageInfo.applicationInfo.labelRes;
        return context.getResources().getString(labelRes);
    }

}
