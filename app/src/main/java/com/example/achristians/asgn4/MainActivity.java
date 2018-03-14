package com.example.achristians.asgn4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

private Button add;
private ListView listRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, EditScreen.class);
                startActivity(i);
            }
        });

        /**
         * List view screen
         * make sure that u
         *
         * sers can click on recipe
         */
        // be able to edit list view (delete)
        //search array list Recipe
        //delete marked recipes
        //https://stackoverflow.com/questions/1102050/how-to-navigate-from-one-screen-to-another-screen
         //Intent i = new Intent(y.this, Activity.class);
        // startActivity(i);
        /**
         * show screen
         */

    }
}
