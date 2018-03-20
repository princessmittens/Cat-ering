package com.example.achristians.recipe_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
public ArrayList recipesList = new ArrayList();
private Button add;
private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = findViewById(R.id.list_view);
        add = findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //https://stackoverflow.com/questions/2736389/how-to-pass-an-object-from-one-activity-to-another-on-android
                Intent i = new Intent(MainActivity.this, EditScreen.class);
                i.putExtra("key", recipesList);
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
        ListAdapter adapter = new ListAdapter(this, R.layout.list_view, recipeList);
        listview.setAdapter(adapter);
    }
}
