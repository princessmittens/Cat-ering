package com.example.achristians.recipe_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;


//public class ListAdapter extends ArrayAdapter {
//    private ArrayList<Recipes> listRecipe;
//
//    public ListAdapter(Context context, int text, ArrayList<Recipes> listRecipe) {
//        super(context, text, listRecipe);
//        this.listRecipe = listRecipe;
//    }
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View v = convertView;
//
//        if (v == null) {
//            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
//                    Context.LAYOUT_INFLATER_SERVICE);
//            v = inflater.inflate(R.layout.list_view, null);
//        }
//        Recipes i = listRecipe.get(position);
//        if (i != null) {
//            TextView name = v.findViewById(R.id.name);
//            RatingBar rating = v.findViewById(R.id.listRB);
//
//            name.setText(i.getName());
////            rating.setRating(Float.parseFloat(i.getRatingRecipe()));
//        }
////        return v;
//  }
//}
