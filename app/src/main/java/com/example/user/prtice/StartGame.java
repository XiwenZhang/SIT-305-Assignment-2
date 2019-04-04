package com.example.user.prtice;


import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartGame extends AppCompatActivity {

     private int days = 1;
     private  int thirty = 0;
     private  int hungry = 0;
     private  int heal = 0;
     public String name;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startpage);


        TextView Days = (TextView) findViewById(R.id.Days);
        Button Newdays = (Button) findViewById(R.id.NewDay);

        TextView Thirty = (TextView) findViewById(R.id.Thirty);
        TextView Hunger = (TextView) findViewById(R.id.Hungry) ;


        String days1 = Integer.toString(days);
        Days.setText(days1);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Button Newdays = (Button) findViewById(R.id.NewDay);
        Button Drink = (Button)findViewById(R.id.Drink);
        Button Eat = (Button) findViewById((R.id.Eat));


        TextView Thirty = (TextView) findViewById(R.id.Thirty);
        TextView Hungry = (TextView) findViewById(R.id.Hungry) ;

        //Drink water to full thirty
        Drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView Thrity = (TextView) findViewById(R.id.Thirty);
                thirty += 20;
                if (thirty >= 100)
                {
                    thirty = 100;
                }

               Thrity.setText(thirty + "/100");
            }
        });

        //Eat to full hunger
        Eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView Hungry = (TextView) findViewById(R.id.Hungry);
                hungry += 20;
                if (hungry >= 100)
                {
                    hungry = 100;
                }

                Hungry.setText(hungry + "/100");
            }

        });




        // when user click new days data update.
        Newdays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // update text view days
                TextView Days = (TextView) findViewById(R.id.Days);
                days += 1;


                String days1 = Integer.toString(days);


                Days.setText(days1);
            }
        });
    }


}
