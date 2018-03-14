package com.example.achristians.asgn4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;



public class EditScreen extends AppCompatActivity {

    ArrayList recipesList = new ArrayList();

    private EditText name, ingredients, description, url;
    private Button save;
    private RatingBar editRB;
    private String date = "25 Jan 1987";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        name = findViewById(R.id.name);
        ingredients = findViewById(R.id.ingredients);
        description = findViewById(R.id.description);
        url = findViewById(R.id.url);
        save = findViewById(R.id.save);
        editRB = findViewById(R.id.editRB);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> list = new ArrayList<>();
                // add all data from edit text
                list.add(description.getText().toString());
                list.add(ingredients.getText().toString());
                list.add(url.getText().toString());
                //list.add(editRB.getRating().toString());

                // populate list view
                recipesList.add(new Recipes(name.getText().toString(), date, list));
            }
            });

    }
}
