package com.zwcwlw.safe.common;

/**
 * 作者：zwcwlw on 2016/8/8 09:55
 * 邮箱:zwcwlw@126.com
 * 描述://TODO
 */

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;


/**
 * 作者：zwcwlw on 2016/8/8 09:49
 * 邮箱:zwcwlw@126.com
 * 描述://TODO
 */
public abstract class MyBaseAdapter<T>  extends BaseAdapter {

    private List<T> data;

    public MyBaseAdapter(List<T> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        T t = data.get(position);
        ViewHolder holder = null;
        if(convertView==null){
            convertView = setView();
            holder = new ViewHolder(convertView);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        setData(convertView,t);
        return convertView;
    }

    //不同view对应的抽象设置数据方法
    protected abstract void setData(View holderView, T t);


    //不同view的抽象实现
    protected abstract View setView();

    private class ViewHolder{
        View view;

        public ViewHolder(View view) {
            this.view = view;
            view.setTag(this);
        }
    }
}
