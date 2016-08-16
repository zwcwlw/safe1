package com.zwcwlw.safe.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zwcwlw.safe.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：zwcwlw on 2016/8/8 11:31
 * 邮箱:zwcwlw@126.com
 * 描述://TODO
 */
public class MyHomeAdapter extends BaseAdapter {

    private Context context;
    String[] names = new String[]{"手机防盗", "骚扰拦截", "软件管家", "进程管理", "流量统计", "手机杀毒", "系统加速", "常用工具"};
    String[] desc = new String[]{"远程定位手机", "全面拦截骚扰", "管理你的软件", "管理正在运行", "流量一目了然", "病毒无处藏身", "系统快如火箭", "常用工具大全"};
    int[] icons = new int[]{R.mipmap.safe_sjfd, R.mipmap.safe_srlj, R.mipmap.safe_rjgj, R.mipmap.safe_jcgl, R.mipmap.safe_lltj, R.mipmap.safe_sjsd, R.mipmap.safe_xtjs, R.mipmap.safe_szzx};

    public MyHomeAdapter(Context context) {
        super();
        this.context = context;
    }


    @Override
    public int getCount() {
        return names == null ? 0 : names.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //  View view = View.inflate(context, R.layout.item_home, null);
        //ImageView ivHomeitemIcon= (ImageView) view.findViewById(R.id.iv_homeitem_icon);
        convertView = LayoutInflater.from(context).inflate(R.layout.item_home, null);
        ViewHolder viewHolder = new ViewHolder(convertView);
        viewHolder.ivHomeitemIcon.setImageResource(icons[position]);
        viewHolder.tvHomeitemTitle.setText(names[position]);
        viewHolder.tvHoemitemDesc.setText(desc[position]);
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.iv_homeitem_icon)
        ImageView ivHomeitemIcon;
        @BindView(R.id.tv_homeitem_title)
        TextView tvHomeitemTitle;
        @BindView(R.id.tv_hoemitem_desc)
        TextView tvHoemitemDesc;
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
