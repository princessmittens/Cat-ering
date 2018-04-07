package com.example.achristians.recipe_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.achristians.asgn4.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class EditScreen extends AppCompatActivity {

    private DatabaseReference mDatabase;

    //    DatabaseReference ref = database.getReference("server/saving-data/fireblog");
    private EditText name, ingredients, description, url;
    private Button save;
    private RatingBar editRB;
    private String date = "25 Jan 1987";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    //   https://stackoverflow.com/questions/5637382/referencing-a-view-in-another-xml-file
        setContentView(R.layout.edit_screen);

        name = findViewById(R.id.name);
        ingredients = findViewById(R.id.ingredients);
        description = findViewById(R.id.description);
        url = findViewById(R.id.url);
        save = findViewById(R.id.save);
        editRB = findViewById(R.id.editRB);

//        editRB.setOnRatingBarChangeListener(new View.OnRatingBarChangeListener() {
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float rating){
//
//            }
//            });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> list = new ArrayList<>();
                // add all data from edit text
                list.add(description.getText().toString());
                list.add(ingredients.getText().toString());
                list.add(url.getText().toString());
                //list.add(editRB.getRating().toString());

                mDatabase = FirebaseDatabase.getInstance().getReference();
                Recipes recipe = new Recipes(list);
                mDatabase.child(name.getText().toString()).setValue(recipe);


                Intent i = new Intent(EditScreen.this, MainActivity.class);
                startActivity(i);
            }
            });

    }
}
