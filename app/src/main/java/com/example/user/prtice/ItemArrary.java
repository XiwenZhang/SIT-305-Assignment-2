package com.example.user.prtice;

import android.content.ClipData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ItemArrary extends ArrayAdapter<ItemAdapter> {
    private TextView Name;
    private TextView Price;


    //setting array adapter
    public ItemArrary(@NonNull Context context, int resource, ArrayAdapter<ItemAdapter> items) {
        super(context, resource, (List<ItemAdapter>) items);
    }
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.singleitem,null);
        }
        ItemAdapter item =getItem(position);
        if(item != null)
        {
            Name = (TextView) convertView.findViewById(R.id.textView1);
            Price = (TextView) convertView.findViewById(R.id.textView2);


            Name.setText(item.getMname());
            Price.setText(item.getMprice());
        }


        return convertView;
    }

}
