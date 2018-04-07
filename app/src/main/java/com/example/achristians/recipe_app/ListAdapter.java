package com.example.achristians.recipe_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.achristians.asgn4.R;

import java.util.ArrayList;


public class ListAdapter extends ArrayAdapter<Recipes> {
    private ArrayList<Recipes> listRecipe;

    public ListAdapter(Context context, int text, ArrayList<Recipes> listRecipe) {
        super(context, text, listRecipe);
        this.listRecipe = listRecipe;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_view, null);
        }

        Recipes i = listRecipe.get(position);
        if (listRecipe.get(position) != null) {
            TextView name = v.findViewById(R.id.name);
            //         TextView tvValue = v.findViewById(R.id.tvValue);

            name.setText(i.getName());
            //   tvValue.setText(String.valueOf(i.getValue()));
        }
        return v;
    }
  }

