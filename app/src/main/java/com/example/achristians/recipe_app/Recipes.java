package com.example.achristians.recipe_app;

import android.os.Parcelable;
import android.widget.RatingBar;

import java.io.Serializable;
import java.util.ArrayList;


class Recipes implements Serializable {
    ArrayList recipesList = new ArrayList();
    ArrayList<String> list = new ArrayList<>();

    public Recipes(ArrayList<String> list) {
        ArrayList recipesList = new ArrayList();
        this.list = list;
    }


    public ArrayList<String> getList() {
        return list;
    }

    public String getName() {
        return list.get(0);
    }

    public String getDescription() {
        return list.get(1);
    }
    public String getIng() {
        return list.get(2);
    }

    public String getURL() {
        return list.get(3);
    }

    public String getRating() {return list.get(4);}

    public void setList(ArrayList<String> list) {
        this.list=list;
    }

    public ArrayList getRecipeList() { return recipesList;}

}
