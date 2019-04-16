package com.example.user.prtice;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyBaseAdapter  extends BaseAdapter {

    private Context mContext;
    private List<ItemAdapter> mList;

    //Context of listView
    public MyBaseAdapter(Context context, List<ItemAdapter> list) {
        mContext = context;
        mList = list;
    }


    @Override
    public int getCount() {
        return mList.size();
}

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    /**
     * get the interface view of list View
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.singleitem,null);
            viewHolder.tv1 = (TextView) convertView.findViewById(R.id.w001);
            viewHolder.tv2 = (TextView) convertView.findViewById(R.id.w002);



        }else
        {   //set view_holder  and get Tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv1.setText(mList.get(position).getMname()+"");
        viewHolder.tv2.setText(mList.get(position).getMprice()+"");
        return convertView;

    }


    public final class ViewHolder{
        TextView tv1,tv2;


    }
}
