package com.zwcwlw.safe.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.zwcwlw.safe.R;
import com.zwcwlw.safe.common.BaseActivity;
import com.zwcwlw.safe.ui.SwitchImageView;
import com.zwcwlw.safe.utils.SPUtils;

import butterknife.BindView;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * 作者：zwcwlw on 2016/8/8 14:15
 * 邮箱:zwcwlw@126.com
 * 描述://TODO
 */
public class SettingActivity extends BaseActivity {
    @BindView(R.id.iv_setting_update)
    SwitchImageView sivSettingUpdate;
    @BindView(R.id.ll_setting_update)
    LinearLayout llSetingUpdate;
    @BindView(R.id.ll_setting_share)
    LinearLayout llSetingUpShare;

    private SharedPreferences sp;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void initData() {
        //设置是否更新
        sivSettingUpdate.setSwitchStatus(SPUtils.getBoolean(SettingActivity.this, "update", true));
        llSetingUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sivSettingUpdate.changeSwitchStatus();
                //保存开关的状态
                SPUtils.setBoolean(SettingActivity.this, "update", sivSettingUpdate.getSwitchStatus());
            }
        });
        //分享到朋友圈
        llSetingUpShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });

    }

    @Override
    public void initTitle() {

    }

    @Override
    public int initView() {
        return R.layout.activity_setting;
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(getString(R.string.share_settitle));
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(getString(R.string.share_seturl));
        // text是分享文本，所有平台都需要这个字段
        oks.setText(getString(R.string.share_settext));
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("sdCard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(getString(R.string.share_seturl));
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment(getString(R.string.share_setcoment));
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.share_setSite));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(getString(R.string.share_seturl));
        // 启动分享GUI
        oks.show(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent myIntent = new Intent();
            myIntent = new Intent(SettingActivity.this, HomeActivity.class);
            startActivity(myIntent);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
