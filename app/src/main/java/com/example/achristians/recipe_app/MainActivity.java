package com.example.achristians.recipe_app;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.achristians.asgn4.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button add;
    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> displayRecipe = new ArrayList<>();
    ArrayList<Recipes> recipesList = new ArrayList();
    private ListAdapter adapter;

    FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference dref = mFirebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView recList = (ListView) findViewById(R.id.list_view);
        add = findViewById(R.id.add);

        adapter = new ListAdapter(this, R.layout.list_view, recipesList);
        recList.setAdapter(adapter);
        recList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                int itemPosition = position;
                Recipes temp = recipesList.get(position);
                Log.d("tag", "this is a test" + temp.getName());
                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " , Toast.LENGTH_LONG)
                        .show();

                Intent i = new Intent(MainActivity.this, ViewScreen.class);
           //     https://stackoverflow.com/questions/5265913/how-to-use-putextra-and-getextra-for-string-data
                Bundle bundle = new Bundle();
                bundle.putSerializable("key", temp);
                i.putExtras(bundle);
                startActivity(i);

            }

        });



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //https://stackoverflow.com/questions/2736389/how-to-pass-an-object-from-one-activity-to-another-on-android
                Intent i = new Intent(MainActivity.this, EditScreen.class);
                startActivity(i);
            }
        });
        FirebaseApp.initializeApp(this);
        // There are many references because I had no idea what I was doing...
        // https://stackoverflow.com/questions/38965731/how-to-get-all-childs-data-in-firebase-database
        // https://stackoverflow.com/questions/42648147/populating-a-listview-with-firebase-data-in-android

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

                        //    System.out.println("DSP " + dsp.child("list").getValue());
                        }
                        adapter.notifyDataSetChanged();
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
    }
};
