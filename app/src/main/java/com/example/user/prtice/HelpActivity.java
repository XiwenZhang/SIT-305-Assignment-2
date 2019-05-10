package com.example.user.prtice;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HelpActivity extends Activity {

    private TextView help;
    private Button next;
    private Button start;
    String msg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.help_page);
        help = findViewById(R.id.helpboard);
        next = findViewById(R.id.next);
        start = findViewById(R.id.start);

        //set text type
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/ftt.otf");
        help.setTypeface(font);



        /**
         * set button on click listener
         */
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNext();
                help.setText(msg);

            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HelpActivity.this,StartGame.class);
                startActivity(i);
            }
        });


    }

    /**
     * set next button on click
     */
    private void setNext()
    {
        msg = "Instructions:" +
                "\n  Thanks for you using 'Rich overnight' app. You should know that left table is market table you can buy any table items and it will save in your right table(MyItems table)."
                + "\n   The price of market will change lower or higher for every single items in market tables. What you should do is 'High price to buy low price to sell' That is easy right?"
                +"\n    By the way, you have 60 weeks to experience this app, Let me see how much you can earn....... "
                ;
    }
}
