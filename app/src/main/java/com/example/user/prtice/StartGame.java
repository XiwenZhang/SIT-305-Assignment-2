


package com.example.user.prtice;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import java.security.PrivateKey;
import java.util.Random;

public class StartGame extends Activity {

     private int days;
     private  int thirty = 0;
     private  int hungry = 0;
     private  int heal = 0;
     public  int random = 0;

     private TextView Days;
     private TextView Board;
     private TextView Thirty;
     private TextView Hungry;

     private Button Hunt;
     private Button Eat;
     private Button Newdays;
     private Button Drink;
     private Button Market;
     private Button MyItem;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startpage);


         Newdays =  findViewById(R.id.NewDay);
         Hunt = findViewById(R.id.Hunt);
         Drink = findViewById(R.id.Drink);
         Eat = findViewById(R.id.Eat);
         Market = findViewById(R.id.Adventure);
         MyItem = findViewById(R.id.Items);


         Thirty = findViewById(R.id.Thirty);
         Hungry = findViewById(R.id.Hungry) ;
         Days = findViewById(R.id.Days);
         Board = findViewById(R.id.board);



        Market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Market();
            }
        });

        Newdays.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                New_days();
                PlayerData();
            }
        });

        Drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drink();
                PlayerData();
            }
        });

        Eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eat_Some();
                PlayerData();
            }
        });

        //Hunt Button intent
        Hunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Hunt();
            }
        });
    }






    /**
     * This void is use to upload board text
     */
    public void PlayerData(){
        if (thirty <= 100 & hungry <= 100 & thirty >= 80 & hungry >= 80) {
            Board.setText("You feel good now, nothing is better than full in this land.");
        } else if (thirty < 80 & hungry <= 80 & thirty > 30 & hungry > 30) {

            Board.setText("You do not feel hungry and thirty, but only so.");
        }
        else if(thirty <= 30 & hungry <= 30)
        {
            Board.setText("You feel hungry and thirty,your pupils begin to expand. You smalled death.");
        }
        if (thirty < 30)
        {
            Board.append("\nYou feel Thirty.");
        }
        if (hungry < 30)
        {
            Board.append("\nYou feel hungry.");
        }
    }


    /**
     * Eat button on click + 20 value
     */

    private void Eat_Some()
    {
        hungry += 20;
        if (hungry >= 100) {
            hungry = 100;
        }

        Hungry.setText(hungry + "/100");

    }

    /**
     * Drink button on click + 20 value
     */
    private void Drink()
    {
        thirty += 20;
        if (thirty >= 100)
        {
            thirty = 100;
        }

        Thirty.setText(thirty + "/100");



    }

    /**
     *    when user click new days data update.
     *    days + 1
     */
    private void New_days()
    {
        // update text view days

        days += 1;
        thirty -= 10;
        hungry -= 10;

        if (thirty < 0)
        {
            thirty = 0;
        }
        if (hungry < 0)
        {
            hungry = 0;
        }

        Thirty.setText(thirty + "/100");
        Hungry.setText(hungry + "/100");
        String days1 = Integer.toString(days);
        Days.setText(days1);
}

    private void Hunt()
    {   Random random1 = new Random();
        random  = random1.nextInt(3) + 1;
        Intent intent = new Intent(StartGame.this,HuntActivity.class);
        intent.putExtra("Random",random);
        startActivity(intent);
    }

    private  void  Market()
    {
        Intent intent = new Intent(StartGame.this,MarketActivity.class);
        startActivity(intent);
    }
}
