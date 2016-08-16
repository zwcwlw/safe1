package com.zwcwlw.safe.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.zwcwlw.safe.R;

/**
 * Created by Administrator on 2016/8/11.
 * 自定义开关的状态imageview
 */
public class SwitchImageView extends ImageView {
    //开关的状态flase为关，true为开
    private boolean switchStatus=true;

    //获取开关的状态
    public boolean getSwitchStatus() {
        return switchStatus;
    }

    public void setSwitchStatus(boolean switchStatus) {
        this.switchStatus = switchStatus;
        if (switchStatus){
            setImageResource(R.mipmap.on);
        }else{
            setImageResource(R.mipmap.off);
        }
    }

    //修改开关的状态
    public void changeSwitchStatus(){
        setSwitchStatus(!switchStatus);
    }

    public SwitchImageView(Context context) {
        super(context);
    }

    public SwitchImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwitchImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
