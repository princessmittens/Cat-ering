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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class EditScreen extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private EditText name, ingredients, description, url, instructions;
    private Button save, delete;
    private RatingBar editRB;
    private String date = "25 Jan 1987";
    boolean editRecipe = false;
    TextView editName;
    Recipes savedRecipe;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_screen);

        // Assign variables to buttons/text-views
        name = findViewById(R.id.name);
        ingredients = findViewById(R.id.ingredients);
        description = findViewById(R.id.description);
        url = findViewById(R.id.url);
        save = findViewById(R.id.save);
        editRB = findViewById(R.id.editRB);
        editName = findViewById(R.id.editName);
        instructions = findViewById(R.id.instructions);
        delete = findViewById(R.id.delete);

        /**
         * Get the intent and retrieve data. If data is present in the bundle,
         * the user would like to edit a recipe.
         * In that case, autofill the edittext fields with the previous saved information
         * so the user can update their information.
         * https://stackoverflow.com/questions/5265913/how-to-use-putextra-and-getextra-for-string-data
         */
        Intent i = getIntent();
        Bundle bundle = i.getExtras();

        if (bundle != null) {
            editRecipe = true;
            savedRecipe = (Recipes) bundle.getSerializable("key");
            name.setText(savedRecipe.getName());
            description.setText(savedRecipe.getDescription());
            ingredients.setText(savedRecipe.getIng());
            instructions.setText(savedRecipe.getInstructions());
            url.setText(savedRecipe.getURL());
            name.setVisibility(View.GONE);
            editName.setVisibility(View.VISIBLE);
            editName.setText(savedRecipe.getName());
            editRB.setRating(Float.parseFloat(savedRecipe.getRating()));
        }

        // Declare Firebase database and get the reference location
        mDatabase = FirebaseDatabase.getInstance().getReference();

        /**
         * If user is editting a recipe, show the delete option. If delete clicked, then search
         * the Firebase Database for that entry and remove it, then navigate back to list view screen
         * and update user on status.
         */
        if (editRecipe == true) {
            //Show delete button
            delete.setVisibility(View.VISIBLE);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDatabase.addListenerForSingleValueEvent(
                            new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    // When clicked, search Firebase Database for the name
                                    for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                                        String nameFromDB = dsp.getKey();
                                        // if the name is present, delete it
                                        if (nameFromDB.equals(savedRecipe.getName())) {
                                            dsp.getRef().removeValue();
                                            System.out.println("deleting" + nameFromDB);
                                            Toast.makeText(getApplicationContext(),
                                                    "Deleting entry."
                                                    , Toast.LENGTH_LONG)
                                                    .show();
                                            Intent i = new Intent(EditScreen.this, MainActivity.class);
                                            startActivity(i);
                                        }
                                    }

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    // Update user on status
                                    Toast.makeText(getApplicationContext(),
                                            "Unable to delete entry."
                                            , Toast.LENGTH_LONG)
                                            .show();
                                }

                            });
                }

            });

        } else {
            // Don't show delete button if the user is creating a new recipe
            delete.setVisibility(View.GONE);
        }

        //When user clicks save button, send the data to firebase
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Make sure the name, description, instructions, ingredient fields are all populated
                if (!((name.getText().toString().equals(""))
                        || (description.getText().toString().equals(""))
                        || (ingredients.getText().toString().equals(""))
                        || (instructions.getText().toString().equals("")))) {

                    // Extract all the data from the EditTexts in 'edit_screen.xml and add to a list
                    ArrayList<String> list = new ArrayList<>();
                    list.add(name.getText().toString());
                    list.add(description.getText().toString());
                    list.add(ingredients.getText().toString());
                    list.add(url.getText().toString());
                    list.add(Double.toString(editRB.getRating()));
                    list.add(instructions.getText().toString());

                    //Create an object and send that object to the Firebase database to be saved
                    Recipes recipe = new Recipes(list);
                    mDatabase.child(name.getText().toString()).setValue(recipe);

                    //Update user on status
                    if (editRecipe == true) {
                        Toast.makeText(getApplicationContext(),
                                "Updating entry."
                                , Toast.LENGTH_LONG)
                                .show();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Creating recipe."
                                , Toast.LENGTH_LONG)
                                .show();
                    }

                    // Navigate back to list-view screen
                    Intent i = new Intent(EditScreen.this, MainActivity.class);
                    startActivity(i);
                } else {
                    // Update user on status
                    Toast.makeText(getApplicationContext(),
                            "Name/Description or Ingredients cannot be empty, please enter" +
                                    " some text!" +
                                    "", Toast.LENGTH_LONG)
                            .show();

                }
            }
        });

    }
}
