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

    TextView viewName, viewDescription, viewIngredients, viewUrl;
     RatingBar viewRB;
    Button editButton;
    Recipes savedRecipe;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_screen);
        // from other screen

        viewName = findViewById(R.id.viewName);
        viewDescription = findViewById(R.id.viewDescription);
        viewIngredients = findViewById(R.id.viewIngredients);
        viewUrl = findViewById(R.id.viewUrl);
       editButton = findViewById(R.id.editButton);
        viewRB = findViewById(R.id.viewRB);

        Intent i= getIntent();
        Bundle bundle = i.getExtras();
        // https://stackoverflow.com/questions/5265913/how-to-use-putextra-and-getextra-for-string-data
        if(bundle!=null) {
            savedRecipe = (Recipes) bundle.getSerializable("key");
            viewName.setText(savedRecipe.getName());
            viewIngredients.setText(savedRecipe.getDescription());
            viewDescription.setText(savedRecipe.getIng());
            viewUrl.setText(savedRecipe.getURL());
            viewRB.setRating(Float.parseFloat(savedRecipe.getRating()));
            viewRB.setIsIndicator(true);
        }

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
