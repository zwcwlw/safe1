package com.zwcwlw.safe.common;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by Administrator on 2015/10/29.
 *
 * 单例模式
 *
 * activity的管理：添加、关闭当前、关闭指定activity、关闭所有，清空栈、返回栈的个数
 */
public class AppManager {

    Stack<Activity> activityStack = new Stack<>();

    private static AppManager activityManager;

    private AppManager() {

    }

    public static AppManager getInstance(){
        if(activityManager == null){
            activityManager = new AppManager();
        }
        return activityManager;
    }

    /**
     * 添加activity到栈当中
     * @param activity
     */
    public void addActivity(Activity activity){
        activityStack.add(activity);
    }

    /**
     * 关闭指定activity
     * @param activity
     */
    public void closeActivity(Activity activity){
        for (Activity temp : activityStack) {
             if(temp.getClass().equals(activity.getClass())){
                 activityStack.remove(temp);
                 activity.finish();
                 break;
             }
        }
    }

    /**
     * 关闭当前activity
     */
    public void closeCurrentActivity(){
        Activity lastElement = activityStack.lastElement();
        activityStack.remove(lastElement);
        lastElement.finish();
    }

    /**
     * 关闭所有activiity，清空栈
     */
    public void clearStack(){
        for (Activity activity : activityStack) {
             activity.finish();
        }
        activityStack.clear();
    }

    /**
     * 返回当前栈中activity的个数
     * @return
     */
    public int getStackSize(){
        return activityStack.size();
    }
}
