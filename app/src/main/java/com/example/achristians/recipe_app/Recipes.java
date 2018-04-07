package com.example.achristians.recipe_app;

import android.widget.RatingBar;

import java.util.ArrayList;


class Recipes {
    ArrayList recipesList = new ArrayList();
 //   String name;
//    RatingBar ratingStar;
    ArrayList<String> list = new ArrayList<>();

    public Recipes(ArrayList<String> list) {
        ArrayList recipesList = new ArrayList();
//        this.ratingStar = ratingStar;
        this.list = list;
    }
//
//    public String getName() {
//        return name;
//    }

//    public Float getRatingRecipe() {
//        Float k = ratingStar;
//        return k;
//    }

    public ArrayList<String> getList() {
        return list;
    }

    public String getName() {
        return list.get(0);
    }
    public void setList(ArrayList<String> list) {
        this.list=list;
    }

    public ArrayList getRecipeList() { return recipesList;}

    public void addRecipe(Recipes recipe) { recipesList.add(recipe); }

}
