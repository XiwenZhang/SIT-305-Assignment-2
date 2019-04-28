package com.example.user.prtice;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScoreListAdapter extends BaseAdapter {

    private Context mcontext;
    private List<String> msocre;

    public ScoreListAdapter (Context context, List<String> score)
    {
        mcontext = context;
        msocre = score;
    }

    @Override
    public int getCount() {
        return msocre.size();
    }

    @Override
    public Object getItem(int position) {
        return msocre.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ScoreListAdapter.ViewHolder b;

        if (convertView == null){
            b = new ScoreListAdapter.ViewHolder();
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.socrelistview,null);
            b.soer1 = convertView.findViewById(R.id.s01);

            convertView.setTag(b);

        }
        else
        {
            b = (ScoreListAdapter.ViewHolder) convertView.getTag();
        }

        b.soer1.setText("" + msocre.get(position));


        return convertView;
    }

    private class ViewHolder {
        TextView soer1;
    }

}
