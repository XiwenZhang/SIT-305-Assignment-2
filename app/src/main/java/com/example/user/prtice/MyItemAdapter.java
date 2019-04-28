package com.example.user.prtice;

public class MyItemAdapter {
    private String name;
    private int price;
    private int count;
    private int id;

    public void setCount2(int count)
    {
        this.count = count;
    }
    public  void setID2(int id){this.id = id;}
    public void setName2(String name)
    {
        this.name = name;
    }

    public void setPrice2(int price)
    {
        this.price = price;
    }

    public String getName(){return name;}

    public int getPrice(){return price;}

    public int getCount(){return count;}
    public int getId(){return id;}
}
