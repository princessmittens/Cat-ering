package com.example.achristians.recipe_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import com.example.achristians.asgn4.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button add;
    ArrayList<String> list = new ArrayList<>();
    ArrayList<Recipes> recipesList = new ArrayList();
    ListAdapter adapter;
    ListView recList;

    //https://firebase.google.com/docs/database/android/start/
    // https://firebase.google.com/docs/database/web/read-and-write
    FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference dref = mFirebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find Id's for button/List view
        recList = findViewById(R.id.list_view);
        add = findViewById(R.id.add);

        //Call ListAdapter to populate the list view
        adapter = new ListAdapter(this, R.layout.list_view, recipesList);
        recList.setAdapter(adapter);

        //If a user clicks on an item in the list view, navigate to view screen and send the recipe
        //information as well.
        recList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                int itemPosition = position;
                Recipes temp = recipesList.get(position);
                Intent i = new Intent(MainActivity.this, ViewScreen.class);
                // https://stackoverflow.com/questions/5265913/how-to-use-putextra-and-getextra-for-string-data
                Bundle bundle = new Bundle();
                bundle.putSerializable("key", temp);
                i.putExtras(bundle);
                startActivity(i);
            }

        });

        // Button to add a new recipe, navigate to EditScreen
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //https://stackoverflow.com/questions/2736389/how-to-pass-an-object-from-one-activity-to-another-on-android
                Intent i = new Intent(MainActivity.this, EditScreen.class);
                startActivity(i);
            }
        });

        //Initializes Firebase for this application
        FirebaseApp.initializeApp(this);

        // Retrieves information from Firebase database and populates ListView
        // https://stackoverflow.com/questions/42648147/populating-a-listview-with-firebase-data-in-android
        // https://stackoverflow.com/questions/38965731/how-to-get-all-childs-data-in-firebase-database
        dref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                            dsp.getValue();
                            //  https://stackoverflow.com/questions/44285521/how-to-retrieve-data-from-firebase-database-which-is-having-nested-array-and-obj
                            GenericTypeIndicator<ArrayList<String>> stringList = new GenericTypeIndicator<ArrayList<String>>() {
                            };
                            list = dsp.child("list").getValue(stringList);
                            recipesList.add(new Recipes(list));
                            System.out.println("list: " + list);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(),
                                "Error connecting to database."
                                , Toast.LENGTH_LONG)
                                .show();
                    }
                });
    }

    //Search bar function that gets the text from the menu bar
    // https://www.youtube.com/watch?v=hoEY2n8CCSk
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.mS);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
                public boolean onQueryTextChange(String newText) {
                recList.setTextFilterEnabled(true);
                 adapter.getFilter().filter(newText);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}

