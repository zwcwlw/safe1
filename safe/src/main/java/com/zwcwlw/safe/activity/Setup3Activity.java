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
public class Setup3Activity extends BaseActivity {
    @BindView(R.id.bt_pre_three)
    Button btprethree;
    @BindView(R.id.bt_next_three)
    Button btnextthree;

    @Override
    protected void initData() {

        btprethree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Setup3Activity.this, Setup2Activity.class));
                finish();
                overridePendingTransition(R.anim.anim_out,R.anim.anim_in);

            }
        });


        btnextthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Setup3Activity.this, Setup4Activity.class));
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
        return R.layout.activity_setup3;
    }
}
