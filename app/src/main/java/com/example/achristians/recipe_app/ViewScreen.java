package com.example.achristians.recipe_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.achristians.asgn4.R;

import java.util.ArrayList;


public class ViewScreen extends AppCompatActivity {

    TextView viewName, viewDescription, viewIngredients, viewUrl, viewInstructions;
    RatingBar viewRB;
    Button editButton;
    Recipes savedRecipe;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_screen);

        viewName = findViewById(R.id.viewName);
        viewDescription = findViewById(R.id.viewDescription);
        viewIngredients = findViewById(R.id.viewIngredients);
        viewUrl = findViewById(R.id.viewUrl);
        editButton = findViewById(R.id.editButton);
        viewInstructions = findViewById(R.id.viewInstructions);
        viewRB = findViewById(R.id.viewRB);

        // Gets recipe data from MainActivity and sets it to the textviews in view_screen.xml
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        if (bundle != null) {
            savedRecipe = (Recipes) bundle.getSerializable("key");
            viewName.setText(savedRecipe.getName());
            viewIngredients.setText(savedRecipe.getIng());
            viewInstructions.setText(savedRecipe.getInstructions());
            viewDescription.setText(savedRecipe.getDescription());
            viewUrl.setText(savedRecipe.getURL());
            viewRB.setRating(Float.parseFloat(savedRecipe.getRating()));
            viewRB.setIsIndicator(true);
        }

        // Takes user to EditScreen as well as sends the current recipe to EditScreen
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ViewScreen.this, EditScreen.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("key", savedRecipe);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }
}
