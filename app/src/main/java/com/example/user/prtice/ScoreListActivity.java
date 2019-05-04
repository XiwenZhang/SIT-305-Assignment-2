package com.example.user.prtice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ScoreListActivity extends AppCompatActivity {


    private ListView listView;
    private Button delete;
    private Button back;
    List<String> list;

    public List<String> load() {
        FileInputStream in = null;
        BufferedReader reader = null;
        ArrayList<String>  i = new ArrayList<String>();

        try {
            //setting open file name
            in = openFileInput("Score");
            //FileInputStream -> InputStreamReader ->BufferedReader
            reader = new BufferedReader(new InputStreamReader(in));
            String line = new String();
            //read data by line to line
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

        back = findViewById(R.id.listback);
        delete = findViewById(R.id.de);
        listView = findViewById(R.id.socrelist);

          list = load();


        ScoreListAdapter a = new ScoreListAdapter(this, list);
        listView.setAdapter(a);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }

    private void Delete()
    {
        File file = new File("/data/data/com.example.user.prtice/files/Score");
        file.delete();
        //add the title of score list
        savefirst();

        //load score list again
        list = load();

        ScoreListAdapter a = new ScoreListAdapter(this, list);
        listView.setAdapter(a);
    }


    private void back()
    {
        Intent i = new Intent(ScoreListActivity.this,MainActivity.class);
        startActivity(i);
    }

    // save the first line of file
    private void  savefirst()
    {       String first = " User History:";
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {

            //setting file and create it
            out = openFileOutput("Score", Context.MODE_PRIVATE);
            //create OutputStreamWriter，passing to BufferedWriter
            writer = new BufferedWriter(new OutputStreamWriter(out));
            //writing data to file
            writer.write(first);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
