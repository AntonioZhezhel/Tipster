package com.example.tipster;

import java.util.ArrayList;
import java.util.List;

public class Item {
    private String mName;


    private int mCost;
    private int mId;

    public Item(String name, int cost, int id) {
        mName = name;
        mCost = cost;
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getCost() {
        return mCost;
    }

    public void setCost(int cost) {
        this.mCost = cost;
    }

    public int getId() {
        return mId;
    }


    public static List<Item> createItemsList(int sen) {
        List<Item> items = new ArrayList<>();
        for (int i = 1; i < sen; i++) {
            items.add(new Item("",0, i));
        }
        return items;
    }
}
