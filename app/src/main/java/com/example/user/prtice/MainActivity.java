package com.example.user.prtice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button start;
    private Button scorelist;
    private Button exit;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button Connection
         start = (Button) findViewById(R.id.Start);
         scorelist = (Button) findViewById(R.id.Scorelist);
         exit = (Button) findViewById(R.id.Exit);

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

