package com.example.user.SIT305Assignment2;

import android.content.Context;
import android.util.TypedValue;
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
            viewHolder.show1 = (TextView) convertView.findViewById(R.id.w001);
            viewHolder.show2 = (TextView) convertView.findViewById(R.id.w002);
            convertView.setTag(viewHolder);


        }else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.show1.setText(" "+ mList.get(position).getMname()+"");
        viewHolder.show2.setText(mList.get(position).getMprice()+"");
        if (position == 0)
        {
            viewHolder.show1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,22);
            viewHolder.show2.setTextSize(TypedValue.COMPLEX_UNIT_DIP,22);

        }


        return convertView;

    }


    public final class ViewHolder{
        TextView show1,show2;

    }
}