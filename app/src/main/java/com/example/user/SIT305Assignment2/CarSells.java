package com.example.user.SIT305Assignment2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CarSells extends Activity {

    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;

    private Button b1;
    private Button b2;
    private Button b3;
    private Button back;

    public int car1 = 0;
    public int car2 = 0;
    public int car3 = 0;
    public int money = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.carsells);
        // set button
        b1 = findViewById(R.id.st);
        b2 = findViewById(R.id.gtr);
        b3 = findViewById(R.id.bg);
        back = findViewById(R.id.button5);

        //set text view
        t1 = findViewById(R.id.textView);
        t2 = findViewById(R.id.textView2);
        t3 = findViewById(R.id.textView3);
        t4 = findViewById(R.id.textView4);

        //set money for each car
        t1.setText("$50,000");
        t2.setText("$200,000");
        t3.setText("$600,000");

        //set font of TextView
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/ftt.otf");
        t1.setTypeface(font);
        t2.setTypeface(font);
        t3.setTypeface(font);
        t4.setTypeface(font);




        //get the money
        Bundle b = this.getIntent().getExtras();
         money = b.getInt("money");
         car1 = b.getInt("carb1");
         car2 = b.getInt("carb2");
         car3 = b.getInt("carb3");

         //check already bought car
        //if car already bought buy btn set to enable
        if (car1 == 1)
        {
            b1.setEnabled(false);
            b1.setText("Sell Out");
        }
        if (car2 == 1)
        {
            b2.setEnabled(false);
            b2.setText("Sell Out");
        }
        if (car3 == 1)
        {
            b3.setEnabled(false);
            b3.setText("Sell Out");

        }

        //money show
        t4.setText("I have: $"+Integer.toString(money));


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check money enough
                if (money < 50000)
                {
                    Toast.makeText(CarSells.this, "You do not have enough money ", Toast.LENGTH_LONG).show();
                }else if ( money >= 50000){
                    // money reset after buy car
                    money = money - 50000;
                    car1 =1;
                    //button set enable after car buy
                    b1.setEnabled(false);
                    //set money
                    t4.setText("I have: $"+Integer.toString(money));
                    b1.setText("Sell Out");

                }

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check money enough
                if (money < 200000)
                {
                    Toast.makeText(CarSells.this, "You do not have enough money ", Toast.LENGTH_LONG).show();
                }else if( money >=200000) {
                    //set money after buy car
                    money = money - 200000;
                    car2 =1;
                    //set button enable
                    b2.setEnabled(false);
                    t4.setText("I have: $"+Integer.toString(money));
                    b2.setText("Sell Out");

                }
            }
        });


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check money enough

                if (money < 600000)
                {
                    Toast.makeText(CarSells.this, "You do not have enough money ", Toast.LENGTH_LONG).show();
                }else if(money >= 600000){
                    // money reset after buy car

                    money = money - 600000;
                    car3 = 1;
                    //set button enable

                    b3.setEnabled(false);
                    t4.setText("I have: $"+Integer.toString(money));
                    b3.setText("Sell Out");

                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent();
                //extra money to next java class
                Bundle bundle = new Bundle();
                bundle.putInt("car1",car1);
                bundle.putInt("car2",car2);
                bundle.putInt("car3",car3);
                bundle.putInt("moneyc",money);
                t.putExtras(bundle);
                setResult(0x717,t);
               finish();




            }
        });

    }




}
