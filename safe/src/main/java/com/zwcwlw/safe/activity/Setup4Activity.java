package com.zwcwlw.safe.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.zwcwlw.safe.R;
import com.zwcwlw.safe.common.BaseActivity;
import com.zwcwlw.safe.utils.SPUtils;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/8/13.
 */
public class Setup4Activity extends BaseActivity {
    @BindView(R.id.bt_pre_foru)
    Button btpreforu;
    @BindView(R.id.bt_next_foru)
    Button btnextforu;

    @Override
    protected void initData() {

        btpreforu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Setup4Activity.this, Setup4Activity.class));
                finish();
                overridePendingTransition(R.anim.anim_out,R.anim.anim_in);
            }
        });

        //完成设置向导
        btnextforu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Setup4Activity.this, Setup5Activity.class));
                finish();
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                //做标记说明我们已经做了设置向导的设置
                SPUtils.setBoolean(Setup4Activity.this, "configed", true);
            }
        });
    }

    @Override
    public void initTitle() {

    }

    @Override
    public int initView() {
        return R.layout.activity_setup4;
    }
}
