package com.example.achristians.recipe_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.achristians.asgn4.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public ArrayList recipesList = new ArrayList();
    private Button add;
    private ListView listview;
//    ArrayList recipeList = new ArrayList<>();
//    Recipes recipelist;
FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference dref = mFirebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView)findViewById(R.id.list_view);
        add = findViewById(R.id.add);

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
                            //Get map of users in datasnapshot
                               dsp.getValue();
                                System.out.println(dsp.getValue());
                            System.out.println("DSP " + dsp.getKey());
                      //      System.out.println("DSP " + dsp.child("list").getValue());
                        }
                    }
          //          listview.setAdapter(mAdapter);
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

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
//        ref.addChildEventListener(new ChildEventListener() {
////            @Override
////            @Override
////            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
////                Post newPost = dataSnapshot.getValue(Post.class);
////
////            }
//

 //       ListAdapter adapter = new ListAdapter(this, R.layout.list_view);
//        listview.setAdapter(adapter);
    }
}
