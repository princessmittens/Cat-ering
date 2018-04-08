package com.example.achristians.recipe_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

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
    boolean editRecipe = false;
    TextView editName;
    Recipes savedRecipe;

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
        editName = findViewById(R.id.editName);

        Intent i= getIntent();
      Bundle bundle = i.getExtras();
        // https://stackoverflow.com/questions/5265913/how-to-use-putextra-and-getextra-for-string-data
        if(bundle!=null) {
            savedRecipe = (Recipes) bundle.getSerializable("key");
            name.setText(savedRecipe.getName());
            description.setText(savedRecipe.getDescription());
            ingredients.setText(savedRecipe.getIng());
            url.setText(savedRecipe.getURL());
            editRecipe = true;
            name.setVisibility(View.GONE);
            editName.setVisibility(View.VISIBLE);
            editName.setText(savedRecipe.getName());

        }

//        editRB.setOnRatingBarChangeListener(new View.OnRatingBarChangeListener() {
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float rating){
//
//            }
//            });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!((name.getText().toString().equals("")) || (description.getText().toString().equals(""))
                        || (ingredients.getText().toString().equals("")))) {
                    ArrayList<String> list = new ArrayList<>();
                    // add all data from edit text
                    list.add(name.getText().toString());
                    list.add(description.getText().toString());
                    list.add(ingredients.getText().toString());
                    list.add(url.getText().toString());
                    //list.add(editRB.getRating().toString());

                    mDatabase = FirebaseDatabase.getInstance().getReference();

                    Recipes recipe = new Recipes(list);
                    mDatabase.child(name.getText().toString()).setValue(recipe);

                    Intent i = new Intent(EditScreen.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Name/Description or Ingredients cannot be empty, please enter" +
                                    " some text!" +
                                    "" , Toast.LENGTH_LONG)
                            .show();

                }
            }
            });

    }
}
