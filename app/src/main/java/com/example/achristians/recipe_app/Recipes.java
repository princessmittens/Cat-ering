package com.example.achristians.recipe_app;

import android.widget.RatingBar;

import java.util.ArrayList;


class Recipes {
    ArrayList recipesList = new ArrayList();
    String name;
    RatingBar ratingStar;
    ArrayList<String> list = new ArrayList<String>();

    public Recipes() {
    }
    public Recipes(String name, ArrayList<String> list) {
        ArrayList recipesList = new ArrayList();
        this.name = name;
        this.ratingStar = ratingStar;
        this.list = list;
    }

    public String getName() {
        return name;
    }

//    public Float getRatingRecipe() {
//        Float k = ratingStar;
//        return k;
//    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list=list;
    }

    public ArrayList getRecipeList() { return recipesList;}

    public void addRecipe(Recipes recipe) { recipesList.add(recipe); }

}
