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
public class Setup1Activity extends BaseActivity {
    @BindView(R.id.bt_setup_next_one)
    Button btnext;

    @Override
    protected void initData() {
        btnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Setup1Activity.this, Setup2Activity.class));
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
        return R.layout.activity_setup1;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
}
