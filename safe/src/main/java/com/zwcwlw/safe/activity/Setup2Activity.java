package com.zwcwlw.safe.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.zwcwlw.safe.R;
import com.zwcwlw.safe.common.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/8/13.
 */
public class Setup2Activity extends BaseActivity {
    @BindView(R.id.bt_pre_two)
    Button btpretwo;
    @BindView(R.id.bt_next_two)
    Button btnexttwo;

    @Override
    protected void initData() {

        btpretwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Setup2Activity.this, Setup1Activity.class));
                finish();
                overridePendingTransition(R.anim.anim_out,R.anim.anim_in);

            }
        });


        btnexttwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Setup2Activity.this, Setup3Activity.class));
                finish();
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);

            }
        });
    }

    @Override
    public void initTitle() {

    }

    @Override
    public int initView() {
        return R.layout.activity_setup2;
    }
}
