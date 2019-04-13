package com.example.user.prtice;

import java.security.PublicKey;

public class ItemAdapter {
    private String mid;
    private String mname;
    private String mprice;

//    //Item setting
//    Items(String id,String name,String price )
//    {   mid = id;
//        mname = name;
//        mprice = price;
//
//    }

    /**
     *package data
     */
    public void setID(String id)
    {
        mid = id;
    }
    public void  setName(String name)
    {
        mname = name;
    }
    public void setPrice(String price)
    {
        mprice = price;
    }

    public String getMid() {
        return mid;
    }
    public String getMname(){
        return mname;
    }
    public String getMprice(){
        return mprice;
    }

    /**
     *
     */





}
