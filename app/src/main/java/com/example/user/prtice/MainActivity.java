package com.example.user.prtice;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button start;
    private Button SS;
    private Button exit;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button Connection
         start = (Button) findViewById(R.id.Start);
         SS = (Button) findViewById(R.id.Scorelist);
         exit = (Button) findViewById(R.id.Exit);

         SS.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(MainActivity.this,ScoreListActivity.class);
                 startActivity(intent);
             }
         });
    }


    //intent to GameStart
    public void StartIntent(View view) {
        Intent intent = new Intent(this,StartGame.class);
        startActivity(intent);
    }
    // exit app
    protected void Exit(View view)
    {
        finish();
    }

    protected void ScoreList(View view){
        Intent intent = new Intent(this,ScoreListActivity.class);
        startActivity(intent);
    }
}

