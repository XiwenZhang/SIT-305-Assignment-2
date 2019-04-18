package com.example.user.prtice;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyScondBaseAdapter extends BaseAdapter {

    private Context mcontext;
    private List<MyItemAdapter> mmyItemAdapters;

    public MyScondBaseAdapter (Context context, List<MyItemAdapter> myItemAdapters)
    {
        mcontext = context;
        mmyItemAdapters = myItemAdapters;
    }

    @Override
    public int getCount() {
        return mmyItemAdapters.size();
    }

    @Override
    public Object getItem(int position) {
        return mmyItemAdapters.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         ViewHolder v;

        if (convertView == null){
            v = new ViewHolder();
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.mysingleitem,null);
            v.s1 = convertView.findViewById(R.id.w01);
            v.s2 = convertView.findViewById(R.id.w02);
            v.s3 = convertView.findViewById(R.id.w03);
            convertView.setTag(v);

        }
        else
        {
            v = (ViewHolder) convertView.getTag();
        }

            v.s1.setText(mmyItemAdapters.get(position).getName());
            v.s2.setText(Integer.toString(mmyItemAdapters.get(position).getCount()));
            v.s3.setText(Integer.toString(mmyItemAdapters.get(position).getPrice()));

        return convertView;
    }

    private class ViewHolder {
        TextView s1,s2,s3;
    }
}
