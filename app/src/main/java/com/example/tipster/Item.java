package com.example.tipster;
import java.util.ArrayList;
import java.util.List;
public class Item {
    private String mName;
    private String mCost;
    private int mId;

    public Item(String name,String cost,int id){
        mName=name;
        mCost=cost;
        mId=id;
    }

    public String getName() { return mName; }
    public void setmName(String mName){ this.mName=mName; }

    public String getCost(){return mCost;}
    public void setmCost(String mCost){this.mCost=mCost;}

    public int getId(){return mId;}



    public static List<Item> createItemsList(int sen){
        List<Item> items=new ArrayList<>();
        for (int i=1;i<sen;i++){
            items.add(new Item("","",i));
        }
        return items;
    }
}
