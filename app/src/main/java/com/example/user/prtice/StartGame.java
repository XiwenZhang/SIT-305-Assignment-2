


package com.example.user.prtice;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.XmlResourceParser;
import android.graphics.Typeface;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StartGame extends Activity {

    private int days;
    private int moneyc = 0;
    private int heal = 0;
    public int random = 0;
    private int event = 1;
    private int event2 = 0;
    private int counts = 0;
    private int itemp = 0;
    private int idm;
    private int itemid = 0;
    private int car1 = 0;
    private int car2 = 0;
    private int car3 = 0;
    private int total_money = 3000;


    private String itemname;
    private int itemprice;
    private String itemn;
    private String msg;
    private String title;
    private int changes;
    private String btn1;
    private String btn2;
    private String name;



    private TextView Display1;
    private TextView Display2;
    private TextView Days;
    private TextView Board;
    private TextView Money;
    private TextView Health;
    private TextView Heal;
    private TextView Display3;
    private TextView Cars;




    private ListView mark;
    private ListView items;


    private Button Hangout;
    private Button Exit;
    private Button Newdays;
    private Button money;
    private Button Carsells;
    private Button Hospital;


    private List<ItemAdapter> sells;
    private List<MyItemAdapter> myitems;
    private List<ItemAdapter> randomList;
    private ItemAdapter itemAdapter;
    private XmlPullParser parser;
    public int cr = 0;
    private ScoreList score;
    private ArrayList<ScoreList> mlist;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startpage);

        //array list
        myitems = new ArrayList<MyItemAdapter>();




        //set value
        moneyc = 3000;
        heal = 100;
        days = 1;

        //Button
        Newdays = findViewById(R.id.NewDay);
        Hangout = findViewById(R.id.Hunt);
        Hospital = findViewById(R.id.Items);
        Exit = findViewById(R.id.Exit);


        //TextView
        Days = findViewById(R.id.Days);
        Money = findViewById(R.id.money);
        Display1 = findViewById(R.id.display1);
        Display2 = findViewById(R.id.display2);
        Health = findViewById(R.id.health);
        Heal = findViewById(R.id.heal);
        Display3 =  findViewById(R.id.display3);
        Carsells = findViewById(R.id.button);
        Cars = findViewById(R.id.cars);




        //ListView
        mark = findViewById(R.id.markee);
        items = findViewById(R.id.myitems);

        //reset List
        sells = null;

        //get value from xml by parser to parser xml film which is saved in assets folder
        sells = parseXmlUsingPull();

        //setting randomList to save the file data
        randomList = parseXmlUsingPull();

        //setting adapter
        MyBaseAdapter a = new MyBaseAdapter(this, sells);
        mark.setAdapter(a);

        Money.setText(Integer.toString(moneyc));


         //this is set font of TextView
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/ftt.otf");
        Days.setTypeface(font);
        Display1.setTypeface(font);
        Display2.setTypeface(font);
        Heal.setTypeface(font);
        Health.setTypeface(font);
        Money.setTypeface(font);
        Display3.setTypeface(font);
        Cars.setTypeface(font);

        /**
         * setting the save file doc
         */







        Newdays.setTypeface(font);


        /**
         *  This is mark Listview item on click listener
         *         - When the single item of ListView is be clicked
         *         - it will shows Dialog and input the count of items to buy.
         *         - If it is the first time to click
         *         - the Myitems ListView will be created
         *         - for the EditText in Dialog, user is only be allowed input int numbers(if user enter empty or 0 it will shows Toast.)
         *         -If the total price less than user's money count, user get items in myitems ListView and spend money to buy.
         *         - If the total price more than user's money count they will Recognized as a provocative to store owner.(Health - 3)
         */

    mark.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                // set EditText and number only
                final EditText editText = new EditText(StartGame.this);
                editText.setText("0");
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);


                if (position > 0) {
                    //AlertDialog for the single item buying counts
                    AlertDialog.Builder builder = new AlertDialog.Builder(StartGame.this);

                    builder.setTitle("How many you want to buy?");
                    builder.setView(editText);
                    builder.setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MyItemAdapter myItemAdapter = null;

                            String m ;

                            m = editText.getText().toString();//get input

                            if (TextUtils.isEmpty(m)) {
                                // if input is empty
                                Toast.makeText(StartGame.this, "You enter a empty number", Toast.LENGTH_SHORT).show();
                            }
                             else if (Integer.parseInt(m) == 0)
                            {   //if input is 0
                                Toast.makeText(StartGame.this, "You enter a 0 for counts", Toast.LENGTH_SHORT).show();

                            }
                            else {
                                myItemAdapter = new MyItemAdapter();

                                //get counts, name, id and price from the mark ListView.
                                counts = Integer.parseInt(m);
                                itemname = sells.get(position).getMname();
                                itemid  = sells.get(position).getMid();
                                itemprice = Integer.parseInt(sells.get(position).getMprice());


                                /*
                                  Determine if money is enough
                                 */
                                if (moneyc >= itemprice * counts)
                                {
                                    //set value into the my item ListView
                                    // myItemAdapter.setName2(itemname);

                                    myItemAdapter.setName2(itemname);
                                    myItemAdapter.setPrice2(itemprice);
                                    myItemAdapter.setCount2(counts);
                                    myItemAdapter.setID2(itemid);
                                    myitems.add(myItemAdapter);

                                //set the View of my item ListView
                                MyScondBaseAdapter scondBaseAdapter = new MyScondBaseAdapter(StartGame.this, myitems);
                                items.setAdapter(scondBaseAdapter);
                                moneyc = moneyc - itemprice * counts;
                                Money.setText(Integer.toString(moneyc));


                                 }else
                                {
                                    Toast.makeText(StartGame.this, "You do not have enough money, and store boss think you are provoking.", Toast.LENGTH_LONG).show();
                                    heal = heal - 3;
                                    Heal.setText(Integer.toString(heal));
                                }



                            }
                        }
                    });
                    builder.setCancelable(false); // setting cannot cancel alertdialog by click blank area

                    builder.show();


                }else

                    {


                    }




        }
    });






    /*
    My item ListView On click
     */
    items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

            int count2 = 0;
            int price2 = 0;
            int id2 = 0;

            final int a = myitems.get(position).getCount();
            // set EditText and number only
            final EditText editText = new EditText(StartGame.this);
            editText.setText(Integer.toString(a));
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);


            //get the single price and id of item
            price2 = myitems.get(position).getPrice();
            id2 = myitems.get(position).getId();
            final String name2 = myitems.get(position).getName();

            //get user in put number
            count2 =Integer.parseInt(editText.getText().toString());


            //AlertDialog for the single item buying counts
            AlertDialog.Builder builder = new AlertDialog.Builder(StartGame.this);

            builder.setTitle("How many you want to sell?");
            builder.setView(editText); //set editText

            // inner final



            final int finalId = id2;
            builder.setPositiveButton("Sell", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    int finalPrice = 0;
                    //get user in put number
                    int finalCount =Integer.parseInt(editText.getText().toString()); ;
                    //final count = sells number by user input
                     if (finalCount == a) {
                         //remove the item of ListView
                         myitems.remove(position);


                         //set new View of ListView
                         MyScondBaseAdapter scondBaseAdapter = new MyScondBaseAdapter(StartGame.this, myitems);
                         items.setAdapter(scondBaseAdapter);



                         //update the money
                         //price = market price
                         //market price find by id
                         for(int i = 0; i<=sells.size();i++)
                         {
                                String sname = sells.get(i).getMname();

                                //find the equal name of position item's name
                             if (sname == name2)
                             {
                                 finalPrice = Integer.parseInt(sells.get(i).getMprice());
                                 break;
                             }
                         }

                         moneyc = moneyc + finalPrice * finalCount;
                         Money.setText(Integer.toString(moneyc));

                     }else if(finalCount < a)
                     {
                        int b = a - finalCount;
                        // if less then count will a - count2 that means show the new number of count in ListView
                         myitems.get(position).setCount2(b);



                         MyScondBaseAdapter scondBaseAdapter = new MyScondBaseAdapter(StartGame.this, myitems);
                         items.setAdapter(scondBaseAdapter);


                         //update the money
                         //price = market price
                         //market price find by id
                         for(int i = 0; i<=sells.size();i++)
                         {
                             String sname = sells.get(i).getMname();

                             //find the equal name of position item's name
                             if (sname == name2)
                             {
                                 finalPrice = Integer.parseInt(sells.get(i).getMprice());
                                 break;
                             }
                         }

                         //update the money
                         moneyc = moneyc + finalPrice * finalCount;
                         Money.setText(Integer.toString(moneyc));


                     }else if(finalCount > a)
                     {

                         //remove the item of ListView
                         myitems.remove(position);
                         //set new View of ListView
                         MyScondBaseAdapter scondBaseAdapter = new MyScondBaseAdapter(StartGame.this, myitems);
                         items.setAdapter(scondBaseAdapter);
                         Toast.makeText(StartGame.this, "You enter a  larger number, and it will sells: "+ a, Toast.LENGTH_LONG).show();

                         //update the money
                         moneyc = moneyc + finalPrice * a;
                         Money.setText(Integer.toString(moneyc));

                     }
                     else if (TextUtils.isEmpty(editText.getText().toString()))

                     { // if user input empty value will Toast the empty number for error
                         Toast.makeText(StartGame.this, "You enter a empty number", Toast.LENGTH_SHORT).show();
                     }



                }
            });
            builder.setCancelable(false); // setting cannot cancel alertdialog by click blank area


            builder.show();
            }
            });




      /*
            This is button on click listener
      */


        Newdays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                New_days();

            }
        });

        Hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHospital();
            }
        });

        //Hunt Button intent
        Hangout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // random events count
                Hangoutevent();
                    //show dailog window
                dailaogoutplay();



            }
        });

        Carsells.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(StartGame.this,CarSells.class);

                //extra money to next java class
                Bundle bundle = new Bundle();

                //put Extra to Car Sells
                //check car sell out
                bundle.putInt("money",moneyc);
                bundle.putInt("carb1",car1);
                bundle.putInt("carb2",car2);
                bundle.putInt("carb3",car3);
                t.putExtras(bundle);
                //using sub activity
                startActivityForResult(t,0x717);

            }
        });


        //set Exit the game page
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameOver();
            }
        });





    }


    @Override
    protected void onResume() {
        super.onResume();





    }

    /**
     * when user click new days data update.
     * days + 1
     * Setting price fluctuation, the price will have fluctuation and
     * the highest  price is the original price * 2
     * The lowest price is original price/2
     * There is a new event happen(about items price ) will shows when the new week
     * Adding game over event, if user heal less than 100. and the event target e = 6
     * the death percentage = (loss heal) * 3 / 100 eg: 97 heal for user there is 6% death percentage
     *
     */
    private void New_days() {



        days += 1;

        if (days == 60)
        {
            GameOver();
        }


        String days1 = Integer.toString(days);
        Days.setText(days1+"/60");


        for(int i =1 ;i < sells.size();i ++)
        {


            Random random = new Random();
            //the price Floating range
            int b = random.nextInt(100)+1;

            //the probability of price fluctuation
            int a = random.nextInt(100);
            int c = 0;
            int d = 0;
            int e = 0;
            double check;

            //get original price
            e = Integer.parseInt(randomList.get(i).getMprice());

            // the probability of price fluctuation
            if (a>= 0 && a <50 ) {
                c = Integer.parseInt(randomList.get(i).getMprice());
                c = c + (c*b/100);
                check = e * 2;

                if (c > check )
                {
                    //get the price from random list
                    c= Integer.parseInt(randomList.get(i).getMprice());
                }
                    sells.get(i).setPrice(Integer.toString(c));
                    MyBaseAdapter myBaseAdapter = new MyBaseAdapter(this, sells);
                    mark.setAdapter(myBaseAdapter);

            }
            else if (a>=50 && a<100)
            {   //ger price
                d = Integer.parseInt(randomList.get(i).getMprice());
                //get the decrease price
                d = d - (d * b/100);
                check = e/2;
                if (d < check)
                {
                    d = Integer.parseInt(randomList.get(i).getMprice());
                }

                sells.get(i).setPrice(Integer.toString(d));
                MyBaseAdapter myBaseAdapter = new MyBaseAdapter(this, sells);
                mark.setAdapter(myBaseAdapter);
            }

        }

        //get the random number e, to trigger different events
        Random r = new Random();
        int e = r.nextInt(6)+1;
            switch (e)
                {
                    case 1:
                        /**
                         * when the weekly event happen, this is gold price decrease event
                         */

                        final AlertDialog.Builder builder = new AlertDialog.Builder(StartGame.this);
                        builder.setTitle("News!!!!");
                        builder.setMessage("Expert research has found that gold is rich in substances that make people's personality change.People are selling their gold.");
                        builder.setPositiveButton("Gold price may reduction", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                            int a = 0;
                                            //find i
                                            for (int i = 0; i < sells.size(); i++)
                                            {       int itid = sells.get(i).getMid();
                                                if (itid == 3)
                                                {
                                                    double changeprice;

                                                    //change price is = original price / 3
                                                    changeprice = Integer.parseInt(randomList.get(i).getMprice())/3;
                                                    int changeprice2 = (int) Math.ceil(changeprice);
                                                    sells.get(i).setPrice(Integer.toString(changeprice2));
                                                    MyBaseAdapter baseAdapter = new MyBaseAdapter(StartGame.this, sells);
                                                    mark.setAdapter(baseAdapter);
                                                    break;
                                                }
                                            }
                            }
                        });
                        builder.setCancelable(false); // setting cannot cancel alertdialog by click blank area
                        builder.show();
                        break;

                    case 2:
                        /**
                         * when the weekly event happen, this is gold price increase event
                         */

                        final AlertDialog.Builder builder2 = new AlertDialog.Builder(StartGame.this);
                        builder2.setTitle("News!!!!");
                        builder2.setMessage("The USD continued to fall in price, and everyone bought a lot of gold..(Gold price increase)");
                        builder2.setPositiveButton("good chance to sell out gold", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //find id
                                for (int i = 0; i < sells.size(); i++)
                                {       int itid = sells.get(i).getMid();

                                    //gold's id is 3, thus if id = 3 the position is gold
                                    if (itid == 3)
                                    {
                                        double changeprice;

                                        //change price is = original price / 3
                                        changeprice = Integer.parseInt(randomList.get(i).getMprice()) * 2;
                                        int changeprice2 = (int) Math.ceil(changeprice);

                                        //set price again to changeprice2
                                        sells.get(i).setPrice(Integer.toString(changeprice2));

                                        //set adapter for ListView
                                        MyBaseAdapter baseAdapter = new MyBaseAdapter(StartGame.this, sells);
                                        mark.setAdapter(baseAdapter);
                                        break;
                                    }
                                }
                            }
                        });
                        builder2.setCancelable(false); // setting cannot cancel alertdialog by click blank area

                        builder2.show();
                        break;


                    case 3:
                        /**
                         * when the weekly event happen, this is Iphone price increase event
                         */

                        final AlertDialog.Builder builder3 = new AlertDialog.Builder(StartGame.this);
                        builder3.setTitle("News!!!!");
                        builder3.setMessage("The Iphone designer claims that he will not continue to design any new Iphone. Iphone prices is rising.");
                        builder3.setPositiveButton("Fine", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //find id
                                for (int i = 0; i < sells.size(); i++)
                                {       int itid = sells.get(i).getMid();

                                    //gold's id is 2, thus if id = 2 the position is Iphone
                                    if (itid == 2)
                                    {
                                        double changeprice;

                                        //change price is = original price * 2
                                        changeprice = Integer.parseInt(randomList.get(i).getMprice()) * 2;
                                        int changeprice2 = (int) Math.ceil(changeprice);

                                        //set price again to changeprice2
                                        sells.get(i).setPrice(Integer.toString(changeprice2));

                                        //set adapter for ListView
                                        MyBaseAdapter baseAdapter = new MyBaseAdapter(StartGame.this, sells);
                                        mark.setAdapter(baseAdapter);
                                        break;
                                    }
                                }
                            }
                        });
                        builder3.setCancelable(false); // setting cannot cancel alertdialog by click blank area

                        builder3.show();

                        break;

                    case 4:
                        /**
                         * when the weekly event happen, this is Iphone price decrease event
                         */

                        final AlertDialog.Builder builder4 = new AlertDialog.Builder(StartGame.this);
                        builder4.setTitle("News!!!!");
                        builder4.setMessage("Apple was sued by a number of companies and exposed scandals. Iphone began to cut prices..");
                        builder4.setPositiveButton("Fine", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //find id
                                for (int i = 0; i < sells.size(); i++)
                                {       int itid = sells.get(i).getMid();

                                    //gold's id is 2, thus if id = 2 the position is Iphone
                                    if (itid == 2)
                                    {
                                        double changeprice;

                                        //change price is = original price / 2
                                        changeprice = Integer.parseInt(randomList.get(i).getMprice()) / 3;
                                        int changeprice2 = (int) Math.ceil(changeprice);

                                        //set price again to changeprice2
                                        sells.get(i).setPrice(Integer.toString(changeprice2));

                                        //set adapter for ListView
                                        MyBaseAdapter baseAdapter = new MyBaseAdapter(StartGame.this, sells);
                                        mark.setAdapter(baseAdapter);
                                        break;
                                    }
                                }
                            }
                        });
                        builder4.setCancelable(false); // setting cannot cancel alertdialog by click blank area

                        builder4.show();

                        break;

                    case 5:


                        /**
                         * when the weekly event happen, this is 2-computer price decrease event
                         */

                        final AlertDialog.Builder builder5 = new AlertDialog.Builder(StartGame.this);
                        builder5.setTitle("News!!!!");
                        builder5.setMessage("HP releases new computer, this computer is cheaper and full-featured, people start sell their old computer. Used computer start reduction");
                        builder5.setPositiveButton("Fine", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //find id
                                for (int i = 0; i < sells.size(); i++)
                                {       int itid = sells.get(i).getMid();

                                    //gold's id is 1, thus if id = 1 the position is computer
                                    if (itid == 1)
                                    {
                                        double changeprice;

                                        //change price is = original price * 2
                                        changeprice = Integer.parseInt(randomList.get(i).getMprice()) / 3;
                                        int changeprice2 = (int) Math.ceil(changeprice);

                                        //set price again to changeprice2
                                        sells.get(i).setPrice(Integer.toString(changeprice2));

                                        //set adapter for ListView
                                        MyBaseAdapter baseAdapter = new MyBaseAdapter(StartGame.this, sells);
                                        mark.setAdapter(baseAdapter);
                                        break;
                                    }
                                }
                            }
                        });
                        builder5.setCancelable(false); // setting cannot cancel alertdialog by click blank area
                        builder5.show();



                        break;

                    case 6:
                        /**
                         * this case is game over case when the health less than 100 Players have a chance to die unexpectedly
                         * (The probability of death is related to the degree of injury)
                         */
                        int checkheal = 100 - heal;
                        if (checkheal != 0)
                        {   //set random number death
                            Random death = new Random();
                            int dead = death.nextInt(50)+1;

                            //set death event
                            if (0<=dead && dead<=checkheal * 3)
                            {
                                /**
                                 * This is death event
                                 */

                                final AlertDialog.Builder builder6 = new AlertDialog.Builder(StartGame.this);
                                builder6.setTitle("News!!!!");
                                builder6.setMessage("A little bit of something will be watched by the god of death. You are not concerned about infection and cause infection because your arm is infected. You spend all your money to pay for medical expenses, and your dreams are broken.");
                                builder6.setPositiveButton(".......", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // death save
                                        //GAME OVER
                                        GameOver();

                                    }
                                });
                                builder6.show();
                            }else
                            {
                                final AlertDialog.Builder builder6 = new AlertDialog.Builder(StartGame.this);
                                builder6.setTitle("News!!!!");
                                builder6.setMessage("You feel the pain in your hand faint.");
                                builder6.setPositiveButton("Should go hospital", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {


                                    }
                                });
                                builder6.show();
                            }
                        }


                        break;
                }




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
                            sell.add(item); //adding list
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


    /**
     * Hang out windows printer
     * it decide by the random number event to create the events of hang out
     * event lottery percentage 40%
     *
     */
    public  void  dailaogoutplay()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(StartGame.this);
        builder.setTitle("Hang out");
        builder.setMessage(msg);
        builder.setPositiveButton(btn1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                 switch (event)
                 {  //get event type and set the on_click
                     case 1:

                         double a;
                         a = moneyc;
                         a = Math.ceil(a * 0.9);
                         moneyc = (int) a;
                         Money.setText(Integer.toString(moneyc));
                         break;
                     case 2:
                         if (moneyc >= 100) {
                             moneyc = moneyc - 100;
                             Money.setText(Integer.toString(moneyc));
                             int lottery = 0;
                             //setting lottery system
                             Random random = new Random();
                             lottery = random.nextInt(99) + 1;

                             if (lottery < 100 && lottery >= 60) {
                                 AlertDialog.Builder builder2 = new AlertDialog.Builder(StartGame.this);
                                 builder2.setTitle("Lottery");
                                 builder2.setMessage("Congratulations on winning($5000)");
                                 builder2.setPositiveButton("Lucky!", new DialogInterface.OnClickListener() {
                                     @Override
                                     public void onClick(DialogInterface dialog, int which) {
                                         moneyc = moneyc + 5000;
                                         Money.setText(Integer.toString(moneyc));
                                     }
                                 });
                                 builder2.setCancelable(false); // setting cannot cancel alertdialog by click blank area

                                 builder2.show();
                             }
                         }else if (moneyc <= 100)
                         {
                             Toast.makeText(StartGame.this, "You do not have enough money", Toast.LENGTH_LONG).show();

                         }

                         break;
                 }
            }
        });
        builder.setNegativeButton(btn2, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (event)
                {
                    case 1:
                        double a;
                        a = moneyc;
                        a = Math.ceil(a * 0.9);
                        moneyc = (int) a;
                        Money.setText(Integer.toString(moneyc));
                        break;
                    case 2:
                        break;

                }
            }
        });



        builder.setCancelable(false); // setting cannot cancel alertdialog by click blank area

        builder.show();
    }

    /**
     * this is random event in hangout
     */
    public void Hangoutevent()
    {
        Random random1 = new Random();


        event2 = random1.nextInt(100);
        //setting percentage of event
        if (0<= event2 && event2<= 20 )
        {
            event = 1;
        }else
        {
            event = 2;
        }


        switch (event){
            case 1:
                msg = "You walked down the street and someone stole the wallet. \n He leave note 'pool man' (money Money has been reduced 10%)";
                btn1 = "fine.";
                btn2 = "Damn!";
                break;
            case 2:
                msg ="You are walking down the street and being attracted by lottery advertisements." +
                        "\n You decide to buy one";
                btn1 = "buy it(100$)";
                btn2 = "This is scam";


        }
    }

    /**
     * Hospital when the heal less than 100
     * user can go to hospital for receiving treatment $500 per heal
     * if money if not enough it will show Toast
     * if heal = 100 it will who AlertDialog
     */
    public void setHospital(){
        //AlerDialog for hospital
        AlertDialog.Builder builder = new AlertDialog.Builder(StartGame.this);
          int  aab = 0;
        String msg1 = "";

        //check heal equal or not to 100
        if (heal == 100)
        {   //if heal is 100 show AlertDialog
            msg1 ="You are very healthy, why come to the hospital?";

            builder.setTitle("Hospital?");
            builder.setMessage(msg1);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                }
            });
            builder.show();

            //if heal less than 100
        }else if (heal < 100)
        {
            //loss heal counts
            aab = 100- heal;

            //check money enough
            if (moneyc >= aab * 500) {


                msg1 = "You have recovered and the cost:  $";


                builder.setTitle("Hospital");//set Title
                builder.setMessage(msg1 + aab * 500);//set ,massage

                final int finalAab = aab; //use final to inner class

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(StartGame.this, "You feel better", Toast.LENGTH_SHORT).show();
                        //reset heal to 100
                        heal = 100;
                        Heal.setText(Integer.toString(heal));

                        //the cost reset
                        moneyc = moneyc - finalAab * 500;
                        Money.setText(Integer.toString(moneyc));
                    }
                });
                builder.show();
            }else if (moneyc < aab*500)
            {
                Toast.makeText(StartGame.this, "You do not have enough money", Toast.LENGTH_SHORT).show();

            }
        }



    }

    /**
     * game over
     */
    public void GameOver(){
        // total money void add in
        totleMoney();

        //AlerDialogs for game over
        final EditText editText = new EditText(StartGame.this);
        AlertDialog.Builder builder = new AlertDialog.Builder(StartGame.this);
        builder.setTitle("Game Over" );
        builder.setMessage("Game over, Your total money is : $" + total_money + "\n Enter Your Name to Save"+"\n"
        + Cars.getText().toString());
        builder.setView(editText);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //get user name
                name = editText.getText().toString();

                //save scores
                saveScore();
                //finish the class
                StartGame.this.finish();

            }
        });
        builder.setNegativeButton("Do not need", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //finish class
                    StartGame.this.finish();
            }
        });

        builder.show();


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x717)
        {
                Bundle bundle = data.getExtras();
                //get sell out car information

                car1 = bundle.getInt("car1");
                car2 =bundle.getInt("car2",0);

                car3 = bundle.getInt("car3",0);
                moneyc = bundle.getInt("moneyc",0);
                Money.setText(Integer.toString(moneyc));

                //set text when user bought car
            Cars.setText(" You Have Cars:  \n");
                if (car1 == 1) {

                    Cars.append(" Santana $50000 \n");
                }

                if (car2 == 1) {
                    Cars.append(" GTR $200000 \n");

                }
                if (car3 == 1) {
                    Cars.append(" Bugatti Veyron $600000\n");

                }



        }


    }

    private void saveScore()
    {

        String data = "Name: "+name + "                   Assets: $"+ total_money;

        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {

                //setting file and create it
                out = openFileOutput("Score", Context.MODE_APPEND);
                //create OutputStreamWriter，passing to BufferedWriter
                writer = new BufferedWriter(new OutputStreamWriter(out));
                //writing data to file
                writer.write("\n" +data);


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
     * setting the total money
     */

    private void totleMoney()
    {
        total_money = 0;
        //add the have not sell items
        for(int i = 0 ; i < myitems.size(); i ++)
        {
            int price = myitems.get(i).getPrice() * myitems.get(i).getCount();
            total_money = total_money + price;
        }
        //add the money of user
        total_money = total_money + moneyc;

        //add cars
        if (car1 == 1)
        {
            total_money = total_money + 50000;
        }

        if (car2 == 1)
        {
            total_money =total_money + 200000;
        }
        if(car3 == 1)
        {
            total_money = total_money + 600000;
        }

    }



}
