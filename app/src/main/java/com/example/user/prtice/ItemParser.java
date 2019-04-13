package com.example.user.prtice;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class ItemParser {

    public static List<ItemAdapter> parserXml(InputStream in) throws Exception {
        List<ItemAdapter> Itemslist = null;

        ItemAdapter itemapadter = null;

        //Get an instance of Xmlpullparser parsing
        XmlPullParser parser = Xml.newPullParser();

        parser.setInput(in, "utf-8");

        int type = parser.getEventType();

        while (type != XmlPullParser.END_DOCUMENT) {
            switch (type) {
                case XmlPullParser.START_DOCUMENT:
                    Itemslist = new ArrayList<ItemAdapter>();
                    break;
                case XmlPullParser.START_TAG://Start read TAG
                    if ("item".equals(parser.getName())) {
                        itemapadter = new ItemAdapter();
                        String id = parser.getAttributeName(0);
                        itemapadter.setID(id);
                    } else if ("name".equals(parser.getName())) {
                        //get next target "name"
                        String name = parser.nextText();
                        itemapadter.setName(name);

                    } else if ("price".equals(parser.getName())) {
                        String price = parser.nextText();
                        itemapadter.setPrice(price);
                    }
                    break;
                case XmlPullParser.END_TAG://解析结束标签
                    if ("itemapadter".equals(parser.getName())) {
                        Itemslist.add(itemapadter);
                    }
                    break;


            }
            type = parser.next();
        }
        return Itemslist;
    }
}
