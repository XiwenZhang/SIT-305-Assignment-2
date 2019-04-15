package com.example.user.prtice;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MarketActivity extends Activity {


    private ListView marketlist;
    private ListView itemlist;
    private TextView test;
    private List<ItemAdapter> sells;
    private XmlPullParser parser;
    private ItemAdapter itemAdapter;


    private Button back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market);
        getindata();
         marketlist = (ListView) findViewById(R.id.markboard);
//        itemlist = findViewById(R.id.itemsboard);
        test = findViewById(R.id.test);







        MyBaseAdapter adapter = new MyBaseAdapter(this,sells);
        marketlist.setAdapter(adapter);



    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.market);










//        try {
//            //get the items from items.xml
//            InputStream inputStream = getAssets().open("xml/items.xml");
//            List<ItemAdapter> item_list = Item.parserXml(inputStream);
//            StringBuffer sb = new StringBuffer();
//            for (ItemAdapter ia:item_list)
//            {
//                sb.append(ia.toString());
//            }
//            //set text
////            ItemArrary item = new ItemArrary(this,R.layout.items);
////            sell.setAdapter(item);
//            test.setText(sb.toString());
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//
//        marketlist.setAdapter(null);
//
//



    }

    /**
     * return the list of items it, name, price as a list
     *
     * @return
     */
//        public List<ItemAdapter> parseXmlUsingPull()
//        {
//            List<ItemAdapter> sell = null;
//            ItemAdapter item = null;
//            XmlResourceParser parser = getResources().getXml(R.xml.items);
//
//
//            try{
//                int eventType = parser.getEventType();
//                while (eventType != XmlPullParser.END_DOCUMENT){
//                    String name = parser.getName();
//                    switch (eventType) {
//                        case XmlPullParser.START_TAG:
//                            if ("allitems".equals(name)) {
//                                sell = new ArrayList<>();
//
//                            }else if ("item".equals(name))
//                            {
//                                item  = new ItemAdapter();
//                                item.setID(Integer.parseInt(parser.getAttributeValue(0)));
//                            }else if ("name".equals(name))
//                            {
//                                item.setName(parser.nextText());
//
//                            }else if ("price".equals(name))
//                            {
//                                item .setPrice(parser.nextText());
//                            }
//                            break;
//                        case XmlPullParser.END_TAG:
//                            if ("item".equals(name)){
//                                sell.add(item);
//                            }
//                            break;
//
//
//
//                    }
//                    eventType= parser.next();
//
//                }
//
//            } catch (XmlPullParserException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return sell;
//        }

    private void getindata()
    {


        try{
            //get assets file
            InputStream inputStream = getAssets().open("items.xml");


            //get pull factory
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

                        //get parser
                parser = factory.newPullParser();

                parser.setInput(inputStream,"UTF-8");

                //get event
                int eventType = parser.getEventType();

                // loop eventType not finish doc
                while (eventType != parser.END_DOCUMENT)
                {

                    String parserName = parser.getName();

                    switch (eventType)
                    {
                        case XmlPullParser.START_DOCUMENT:
                            sells = new ArrayList<ItemAdapter>();
                            break;


                        case XmlPullParser.START_TAG:
                            if ("item".equals(parserName))
                            {
                                itemAdapter  = new ItemAdapter();
                                itemAdapter.setID(Integer.parseInt(parser.getAttributeValue(0)));
                            }else if ("name".equals(parserName))
                            {
                                String name = parser.nextText();
                                itemAdapter.setName(name);
                            }else if ("price".equals(parserName))
                            {
                                String name = parser.nextText();
                                itemAdapter.setName(name);
                            }

                    }

                }
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
