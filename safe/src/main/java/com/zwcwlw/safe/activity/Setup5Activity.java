package com.zwcwlw.safe.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zwcwlw.safe.R;
import com.zwcwlw.safe.common.BaseActivity;

import butterknife.BindView;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Administrator on 2016/8/13.
 */
public class Setup5Activity extends BaseActivity {
    @BindView(R.id.ll_setupac5_share)LinearLayout llshare;


    @Override
    protected void initData() {
        llshare.setOnClickListener(new View.OnClickListener() {
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
        return R.layout.activity_setup5;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, HomeActivity.class));
        finish();
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
        oks.setImagePath("http://image.baidu.com/search/down?tn=download&ipn=dwnl&word=download&ie=utf8&fr=result&url=http%3A%2F%2Ftupian.qqjay.com%2Ftou3%2F2015%2F0831%2F833ec0ae5be83e39a2ceeeb416b87e17.jpg");//确保SDcard下面存在此张图片
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

}
