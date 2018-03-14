package com.example.achristians.asgn4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.EventLogTags;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;


public class ViewScreen extends AppCompatActivity {

    TextView viewName, viewDescription, viewIngredients, viewUrl;
    RatingBar viewRB;

    // delete when do listview
    Recipes name;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // from other screen

        viewName = findViewById(R.id.viewName);
        viewDescription = findViewById(R.id.viewDescription);
        viewIngredients = findViewById(R.id.viewIngredients);
        viewUrl = findViewById(R.id.viewUrl);
        viewRB = findViewById(R.id.viewRB);

        viewName.setText(name.getName());
        ArrayList<String> recipeList = name.getList();
        viewDescription.setText(recipeList.get(0));
        viewIngredients.setText(recipeList.get(1));
        float rating = Float.parseFloat(recipeList.get(2));

        //https://stackoverflow.com/questions/9750823/set-value-in-ratingbar
        viewRB.setRating(rating);

    }
}
