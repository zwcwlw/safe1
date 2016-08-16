package com.zwcwlw.safe.common;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import butterknife.ButterKnife;
import cn.sharesdk.framework.ShareSDK;

/**
 * 作者：zwcwlw on 2016/8/6 10:36
 * 邮箱:zwcwlw@126.com
 * 描述://TODO
 */
public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShareSDK.initSDK(this," 1612a22a37800");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(initView());
        //根据自己的实际情况去决定了
        ButterKnife.bind(this);
        //加入到自己的activity栈当中管理
        AppManager.getInstance().addActivity(this);
        initTitle();
        initData();
    }

    protected abstract void initData();

    public abstract void initTitle();

    public abstract int initView();

}
