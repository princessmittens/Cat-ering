package com.example.achristians.asgn4;

import java.util.ArrayList;

/**
 * Created by AChristians on 2018-03-13.
 */

class Recipes {
    String name;
    String date;
    ArrayList<String> list = new ArrayList<String>();

    public Recipes(String name, String date, ArrayList<String> list) {
        this.name = name;
        this.date = date;
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list=list;
    }

}
