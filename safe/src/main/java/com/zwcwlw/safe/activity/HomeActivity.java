package com.zwcwlw.safe.activity;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zwcwlw.safe.R;
import com.zwcwlw.safe.common.BaseActivity;
import com.zwcwlw.safe.utils.Md5Utils;
import com.zwcwlw.safe.utils.SPUtils;
import com.zwcwlw.safe.utils.T;

import butterknife.BindView;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.iv_home_logo)
    ImageView ivHomeLogo;

    @BindView(R.id.tv_home_title)
    TextView tvHomeTitle;

    @BindView(R.id.gv_home_item)
    GridView gvHomeItem;
    protected static final String TAG = "HomeActivity";
    private Button bt_dialog_ok;
    private Button bt_dialog_cancle;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    @Override
    public int initView() {
        return R.layout.activity_home;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void initTitle() {
        ObjectAnimator oa = ObjectAnimator.ofFloat(ivHomeLogo, "rotationY", 30, 60, 90, 120, 150, 180, 210, 240, 270, 300, 330, 360);
        oa.setRepeatCount(ObjectAnimator.INFINITE);
        oa.setDuration(3000);
        oa.setRepeatMode(ObjectAnimator.RESTART);
        oa.start();
    }

    @Override
    protected void initData() {
        //加载主页功能模块
        Context context = HomeActivity.this;
        gvHomeItem.setAdapter(new MyHomeAdapter(context));
        //跳转到设置中心区
        ivHomeLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //给gridview条目设置 的点击事件
        gvHomeItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0://手机防盗
                        //判断用户是否设置过密码
                        if (isSetupPwd()) {
                            //已经设置过密码 弹出输入密码对话框
                            showEnterDialog();
                        } else {
                            //弹出密码设置对话框设置密码
                            showSetupPwdDialog();
                        }
                        break;
                    case 1://骚挠拦截
                        break;
                    case 2://软件管家
                        break;
                    case 3://进程管理
                        break;
                    case 4://流量统计
                        break;
                    case 5://手机杀毒
                        break;
                    case 6://系统加速
                        break;
                    case 7://常用工具
                        break;

                    default:
                        break;


                }
            }
        });
    }


    //弹出设置密码对话框
    private AlertDialog dialog;

    private void showSetupPwdDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = View.inflate(this, R.layout.dialog_setup_pwd, null);
        final EditText et_pwd = (EditText) view.findViewById(R.id.et_dialog_pwd);
        final EditText et_pwd_confirm = (EditText) view.findViewById(R.id.et_dialog_pwd_confirm);
        bt_dialog_ok = (Button) view.findViewById(R.id.bt_dialog_ok);
        bt_dialog_cancle = (Button) view.findViewById(R.id.bt_dialog_cancle);
        //点击取消的对话框关闭密码对话框
        bt_dialog_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        bt_dialog_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = et_pwd.getText().toString().trim();
                String pwd_confirm = et_pwd_confirm.getText().toString().trim();
                if (TextUtils.isEmpty(pwd) || TextUtils.isEmpty(pwd_confirm)) {
                    T.showShort(HomeActivity.this, "密码不能为空");
                    return;
                }
                if (!pwd.equals(pwd_confirm)) {
                    T.showShort(HomeActivity.this, "两次输入的密码不一致");
                    return;
                }
                if (pwd.length() < 6) {
                    T.showShort(HomeActivity.this, "密码必须要六位数");
                    return;
                }
                //存入密码到SP中
                SPUtils.setString(HomeActivity.this, "password", Md5Utils.encode(pwd));
                dialog.dismiss();
                T.showShort(HomeActivity.this, "初始密码已经设置成功");

            }
        });
        builder.setView(view);
        dialog = builder.show();
    }


    //弹出登录dao手机定位
    private void showEnterDialog() {
        AlertDialog.Builder builderEnter = new AlertDialog.Builder(this);
        View view = View.inflate(HomeActivity.this, R.layout.dialog_enter_pwd, null);

        final EditText passwordEnter = (EditText) view.findViewById(R.id.et_enter_pwd);
        bt_dialog_ok = (Button) view.findViewById(R.id.bt_enter_ok);
        bt_dialog_cancle = (Button) view.findViewById(R.id.bt_enter_cancle);

        //判断密码正确后进入到手机定位Activity
        bt_dialog_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String spPw = SPUtils.getString(HomeActivity.this, "password", null);
                String pw = passwordEnter.getText().toString().trim();
                if (TextUtils.isEmpty(Md5Utils.encode(pw)) || pw.length() < 6) {
                    T.showShort(HomeActivity.this, "密码输入错误请重新输入");
                    return;
                }
                if (!Md5Utils.encode(pw).equals(spPw)) {
                    T.showShort(HomeActivity.this, "密码输入错误请重新输入");
                    return;
                }
                dialog.dismiss();

                //判断有没有进入过设置向导页面的设置
                boolean configed = SPUtils.getBoolean(HomeActivity.this, "configed", false);
                if (configed) {
                    startActivity(new Intent(HomeActivity.this, Setup5Activity.class));
                    finish();
                } else {
                    startActivity(new Intent(HomeActivity.this, Setup1Activity.class));
                    finish();
                }
            }
        });

        //取消登录对话框
        bt_dialog_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        builderEnter.setView(view);
        dialog = builderEnter.show();
    }


    //判断用户是否设置过密码
    private boolean isSetupPwd() {
        String password = SPUtils.getString(this, "password", null);
        if (TextUtils.isEmpty(password)) {
            return false;
        } else {
            return true;
        }
    }

}