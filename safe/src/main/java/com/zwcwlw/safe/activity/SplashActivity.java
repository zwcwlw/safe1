package com.zwcwlw.safe.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.zwcwlw.safe.R;
import com.zwcwlw.safe.utils.AppUtils;
import com.zwcwlw.safe.utils.SDCardUtils;
import com.zwcwlw.safe.utils.SPUtils;
import com.zwcwlw.safe.utils.StreamTools;
import com.zwcwlw.safe.utils.T;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 作者：zwcwlw on 2016/7/26 16:23
 * 邮箱:zwcwlw@126.com
 */
public class SplashActivity extends Activity {
    private static final int ERRORURL = 2;
    private static final int ERRORIO = 3;
    private static final int ERRORJSON = 4;
    private static final int TO_HOME_ACTIVITY = 5;
    private TextView tv_splash_version;
    private static final String TAG = "SplashActivity";
    private static final int SHOW_UPDATE_DIALOG = 1;
    private String downloadpath = null;
    private ProgressDialog pd;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_UPDATE_DIALOG:
                    String desc = (String) msg.obj;
                    showUpdateDialog(desc);
                    break;
                case ERRORURL:
                case ERRORIO:
                case ERRORJSON:
                    T.showShort(SplashActivity.this, (msg.obj).toString());
                    toHomeActivity();
                case TO_HOME_ACTIVITY:
                    toHomeActivity();
                    break;
                default:
                    break;
            }
        }
    };
    private JSONObject mJson;
    private String mServerVersion;
    private String mDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //获取本apk版本号并设置到SplashActivity上
        tv_splash_version = (TextView) findViewById(R.id.tv_splash_version);
        tv_splash_version.setText("版本号:" + AppUtils.getVersionName(SplashActivity.this));

        //检查设置的sp为true还是为false
        boolean update = SPUtils.getBoolean(SplashActivity.this, "update", true);
        //获取服务器的版本号
        if (update) {
            new Thread(new checkVersionTask()).start();
        }else {
            new Thread(){
                @Override
                public void run() {
                    SystemClock.sleep(1000);
                    toHomeActivity();
                }
            }.start();

        }
    }

    /*
     *  获取服务器的版本号
     */
    private class checkVersionTask implements Runnable {
        final long startTime = System.currentTimeMillis();

        @Override
        public void run() {
            Message message = Message.obtain();
            HttpURLConnection conn = null;
            String urlPath = getResources().getString(R.string.url);
            try {
                URL url = new URL(urlPath);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(2000);
                conn.setReadTimeout(2000);
                int code = conn.getResponseCode();
                if (code == 200) {
                    InputStream is = conn.getInputStream();
                    String result = StreamTools.readStream(is);
                    //解析json的字符串获取服务器的版本号
                    mJson = new JSONObject(result);
                    mServerVersion = mJson.getString("version");
                    mDescription = mJson.getString("description");
                    downloadpath = mJson.getString("downloadurl");
                    //本地的版本号
                    String localVersion = AppUtils.getVersionName(SplashActivity.this);
                    System.out.print(mServerVersion + "---------");
                    //  L.e("服务器版本",serverVersion+"------");
                    //比对版本号是否一致
                    Log.d("localVersion:" + localVersion, "serverVersion:" + mServerVersion);
                    if (localVersion.equals(mServerVersion)) {
                        //版本号一致,无需升级跳转到主界面
                        message.what = TO_HOME_ACTIVITY;
                    } else {
                        //版本号不一致,提示用户升级,弹出对话框
                        message.what = SHOW_UPDATE_DIALOG;
                        message.obj = mDescription;
                    }
                }
            } catch (MalformedURLException e) {
                // url错误的异常
                message.what = ERRORURL;
                message.obj = "请检查url";
                e.printStackTrace();
            } catch (IOException e) {
                // 网络错误异常
                message.what = ERRORIO;
                message.obj = "网络错误";
                e.printStackTrace();
            } catch (JSONException e) {
                // json解析失败
                message.what = ERRORJSON;
                message.obj = "解析json失败";
                e.printStackTrace();
            } finally {
                long endTime = System.currentTimeMillis();
                long timeUsed = endTime - startTime;// 访问网络花费的时间
                if (timeUsed < 2000) {
                    // 强制休眠一段时间,保证闪屏页展示2秒钟
                    try {
                        Thread.sleep(2000 - timeUsed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                mHandler.sendMessage(message);
                if (conn != null) {
                    conn.disconnect();// 关闭网络连接
                }
            }
        }

    }

    /**
     * 显示自动弹出对话框:立刻升级就跳转到下载apk,下次再说就跳转到主页
     */
    private void showUpdateDialog(String desc) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置对话框不能被用户取消
        builder.setCancelable(false);
        if (mServerVersion == null) {
            try {
                mServerVersion = mJson.getString("version");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        builder.setTitle("升级提醒,最新版本号:" + mServerVersion);
        builder.setMessage(desc);
        builder.setPositiveButton("立刻升级", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                pd = new ProgressDialog(SplashActivity.this);
                pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pd.show();
                downloadAPK();
            }
        });
        builder.setNegativeButton("下次再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //跳转到主界面
                toHomeActivity();
            }
        });
        builder.show();
    }

    /**
     * 下载APK的方法
     */
    private void downloadAPK() {
        HttpUtils http = new HttpUtils();
        File sdDir = SDCardUtils.getExternalStorDirectory();
        File file = new File(sdDir, "安全卫士" + SystemClock.uptimeMillis() + ".apk");
        if (SDCardUtils.isSDCardEnable()) {
            http.download(downloadpath, file.getAbsolutePath(), new RequestCallBack<File>() {
                @Override
                public void onLoading(long total, long current, boolean isUploading) {
                    pd.setMax((int) total);
                    pd.setProgress((int) current);
                    super.onLoading(total, current, isUploading);
                }

                @Override
                public void onSuccess(ResponseInfo<File> responseInfo) {
                    pd.dismiss();
                    T.showShort(SplashActivity.this, "下载成功");
                    //安装APK
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setDataAndType(Uri.fromFile(responseInfo.result), "application/vnd.android.package-archive");
                    startActivity(intent);
                }

                @Override
                public void onFailure(HttpException e, String s) {
                    T.showShort(SplashActivity.this, "下载失败");
                    pd.dismiss();
                    toHomeActivity();

                }
            });
        } else {
            T.showShort(SplashActivity.this, "SD卡不存在");
            toHomeActivity();
        }

    }

    // 如果用户取消安装的话,回调此方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        toHomeActivity();
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void toHomeActivity() {
        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(intent);
        //关闭自己从任务栈退出
        finish();
    }


    /**
     * 异步自定义圆环进度条

    class ProgressAnimation extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            //进度值不断的变化
            for (int i = 0; i < progressView.getMax(); i++) {
                try {
                    publishProgress(i);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //更新进度值
            progressView.setProgress(values[0]);
            progressView.invalidate();
            super.onProgressUpdate(values);
        }
    }
     */


}
