package com.example.user.prtice;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ScoreListActivity extends AppCompatActivity {


    private ListView listView;
    List<String> list;

    public List<String> load() {
        FileInputStream in = null;
        BufferedReader reader = null;
        ArrayList<String>  i = new ArrayList<String>();

        try {
            //设置将要打开的存储文件名称
            in = openFileInput("Score");
            //FileInputStream -> InputStreamReader ->BufferedReader
            reader = new BufferedReader(new InputStreamReader(in));
            String line = new String();
            //读取每一行数据，并追加到StringBuilder对象中，直到结束
            while ((line = reader.readLine()) != null) {

                i.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return i;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.socrelist);

        listView = findViewById(R.id.socrelist);
          list = load();


        ScoreListAdapter a = new ScoreListAdapter(this, list);
        listView.setAdapter(a);



    }
}
