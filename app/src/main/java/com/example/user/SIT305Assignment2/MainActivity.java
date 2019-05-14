package com.example.user.SIT305Assignment2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity {

    private Button start;
    private Button SS;
    private Button exit;
    private Button about;
    private TextView title;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check save data is or not create
        checkfile();
        //Button Connection
         start = (Button) findViewById(R.id.Start);
         SS = (Button) findViewById(R.id.Scorelist);
         exit = (Button) findViewById(R.id.Exit);
        title = findViewById(R.id.textView5);
        about = findViewById(R.id.About);

        //set text type
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/ftt.otf");
        title.setTypeface(font);



        //set bold of font
        TextPaint tp = title.getPaint();
        tp.setFakeBoldText(true);


        SS.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(MainActivity.this,ScoreListActivity.class);
                 startActivity(intent);
             }
         });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,AboutActivity.class);
                    startActivity(i);
            }
        });
    }


    //intent to GameStart
    public void StartIntent(View view) {
        Intent intent = new Intent(this,HelpActivity.class);
        startActivity(intent);
    }
    // exit app
    protected void Exit(View view)
    {
        System.exit(0);//exit app
    }

    protected void ScoreList(View view){
        //intent page
        Intent intent = new Intent(this,ScoreListActivity.class);
        startActivity(intent);
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

    /**
     * check the "Score" is or not create
     */
    private void  checkfile()
    {
        //check the "Score" is or not create
        File f = new File("/data/data/com.example.user.prtice/files/Score");
        if (!f.exists())
        {   // if file not create, create it
            savefirst();
        }
    }

}

