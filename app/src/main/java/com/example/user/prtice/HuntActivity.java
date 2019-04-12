package com.example.user.prtice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class HuntActivity extends Activity {
    public int event_count;

    private Button C1;
    private Button C2;
    private Button Ok;

    private TextView Board1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hunt);




        Board1 = findViewById(R.id.board1);
        C1 = findViewById(R.id.c1);
        C2 = findViewById((R.id.c2));
        Ok = findViewById(R.id.ok);

        Ok.setText("Leave");
        Intent intent = getIntent();
        event_count = intent.getIntExtra("Random",0);
        Event(event_count);



    }


    public void Ok_task(View view) {
        this.finish();

    }

    private void Event(int i) {
        switch (i) {
            case 0:
                Board1.setText("Cannot get the correct int");

            case 1:
                Board1.setText("You saw a wild boar." + "\nIt looks very strong, but hunting it should get a lot of food.\nmake your choice");
                C1.setText("Use traps");
                C2.setText("Waiting for chance and attack");
                break;


            case 2:
                 Board1.setText("You saw a hare." + "\n You believe it can be your best dinner.");
                 C1.setText("Hunting!!");
                 C2.setText("Waiting for chance and attack");
                 break;

            case 3:
                Board1.setText("You did not find any thing." + "\n You wisted all time.");
                C1.setText("Sad!");
                C2.setText("I must find something");
                break;
        }

    }
}