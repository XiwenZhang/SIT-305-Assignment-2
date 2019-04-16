


package com.example.user.prtice;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StartGame extends Activity {

    private int days;
    private double moneyc = 3000;
    private int hungry = 0;
    private int heal = 0;
    public int random = 0;
    private int event = 1;
    private String msg;
    private String title;
    private int changes;
    private String btn1;
    private String btn2;



    private TextView Display1;
    private TextView Display2;
    private TextView Days;
    private TextView Board;
    private TextView Money;
    private TextView Hungry;
    private ListView mark;


    private Button Hangout;
    private Button Eat;
    private Button Newdays;
    private Button Drink;
    private Button Market;
    private Button MyItem;

    private List<ItemAdapter> sells;
    private ItemAdapter itemAdapter;
    private XmlPullParser parser;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startpage);



        //Button
        Newdays = findViewById(R.id.NewDay);
        Hangout = findViewById(R.id.Hunt);
        MyItem = findViewById(R.id.Items);


        //TextView
        Days = findViewById(R.id.Days);
        Money = findViewById(R.id.money);
        Display1 = findViewById(R.id.display1);
        Display2 = findViewById(R.id.display2);

        //ListView
        mark = findViewById(R.id.markee);

        //reset List
        sells = null;

        //get value from xml by parser
        sells = parseXmlUsingPull();


        //setting adapter
        MyBaseAdapter a = new MyBaseAdapter(this, sells);
        mark.setAdapter(a);


        // random events count
        Hangoutevent();

        // this is set font of view
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/ftt.otf");


        Days.setTypeface(font);
        Display1.setTypeface(font);
        Display2.setTypeface(font);


      /*
            This is button on click listener
      */

        Newdays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                New_days();

            }
        });



        //Hunt Button intent
        Hangout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dailaogoutplay();



            }
        });
    }





    /**
     * Eat button on click + 20 value
     */

    private void Eat_Some() {
        hungry += 20;
        if (hungry >= 100) {
            hungry = 100;
        }

        Hungry.setText(hungry + "/100");

    }



    /**
     * when user click new days data update.
     * days + 1
     */
    private void New_days() {

        String days1 = Integer.toString(days);
        Days.setText(days1);
    }

    /**
     * intent page
     */

    private void Market() {
//        Intent intent = new Intent(StartGame.this, MarketActivity.class);
//        startActivity(intent);
    }

    /**
     * Get in data from items.xml in assets
     */
    public List<ItemAdapter> parseXmlUsingPull() {
        List<ItemAdapter> sell = null;
        ItemAdapter item = null;
        XmlResourceParser parser = getResources().getXml(R.xml.items);


        try {
            /**
             * Start parsing the document event: XmlPullParser.START_DOCUMENT—>0
             * End parsing document event: XmlPullParse.END_DOCUMENT _>1
             * Start parsing tag event: XmlPullParse.START_TAG —>2
             * End parsing tag event: XmlPullParse.END_TAG —>3
             * Parsing text event: XmlPullParse.TEXT —>4
             */
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String name = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("allitems".equals(name)) {
                            sell = new ArrayList<>();

                        } else if ("item".equals(name)) {   //get the item and create a new ItemAdapter to save data
                            //get the ID of single item
                            item = new ItemAdapter();
                            item.setID(Integer.parseInt(parser.getAttributeValue(0)));
                        } else if ("name".equals(name)) {   //get item name
                            item.setName(parser.nextText());

                        } else if ("price".equals(name)) {   //get item price
                            item.setPrice(parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("item".equals(name)) {
                            //save those into list<Adapter>
                            sell.add(item);
                        }
                        break;


                }
                eventType = parser.next();

            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return list
        return sell;

    }

    public  void  dailaogoutplay()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(StartGame.this);
        builder.setTitle("Hang out");
        builder.setMessage(msg);
        builder.setPositiveButton(btn1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                 switch (event)
                 {
                     case 1:
                         moneyc = Math.ceil(moneyc * 0.9);
                 }
            }
        });
        builder.setPositiveButton(btn2, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
    }

    /**
     * this is random event in hangout
     */
    public void Hangoutevent()
    {
        Random random1 = new Random();
        event = random1.nextInt(2) + 1;


        switch (event){
            case 1:
                msg = "You walked down the street and someone stole the wallet. \n He leave note 'pool man' (money Money has been reduced 10%)";
                btn1 = "fine.";
                btn2 = "Damn!";
                break;
            case 2:
                msg ="You are walking down the street and being attracted by lottery advertisements." +
                        "\n You decide to buy one";
                btn1 = "buy it(10$)";
                btn2 = "This is scam";


        }
    }

}
