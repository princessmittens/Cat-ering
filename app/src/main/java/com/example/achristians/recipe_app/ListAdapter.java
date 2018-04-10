package com.example.achristians.recipe_app;

import android.content.Context;
import android.content.Intent;
import android.media.Rating;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.achristians.asgn4.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class ListAdapter extends ArrayAdapter<Recipes> {
    //Declare variables
    private ArrayList<Recipes> listRecipe;
    TextView name;
    RatingBar listRB;
    String viewName;
    /**
     * Local constructor for Array Adapter that takes the list of recipes from MainActivity and
     *  populates the list view
     */
    public ListAdapter(Context context, int text, ArrayList<Recipes> listRecipe) {
        super(context, text, listRecipe);
        this.listRecipe = listRecipe;
    }

//    public void refreshEvents(ArrayList<Recipes> listRecipe) {
//        this.listRecipe.clear();
//        this.listRecipe.addAll(listRecipe);
//        notifyDataSetChanged();
//    }


//    @NonNull
//    @Override
//    public Filter getFilter() {
//        return super.getFilter();
//        if(newText != null) {
//            ArrayList<Recipes> tempRecipesList = new ArrayList<>();
//            tempRecipesList = recipesList;
//            //     recipesList.clear();
//            for (int i = 0; i < tempRecipesList.size(); i++) {
//                Recipes temp = tempRecipesList.get(i);
//                if (temp.getName().toLowerCase().contains(newText.toLowerCase())) {
//                    recipesList.add(temp);
//                    String s = temp.getName();
//                    System.out.println("THIS IS A TEST " + temp.getName());
//                    //                   recList.setAdapter(adapter);
//                    Toast.makeText(getApplicationContext(),
//                            "text " + s
//                            ,Toast.LENGTH_LONG)
//                            .show();
//                    adapter.notifyDataSetChanged();
//                }
//            }
//            //     recipesList = tempList;
//
//        }
//    }

    // Function that populates the list view with data from listRecipe
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_view, null);
        }

        Recipes i = listRecipe.get(position);
        if (listRecipe.get(position) != null) {
            name = v.findViewById(R.id.name);
            listRB = v.findViewById(R.id.listRating);

            name.setText(i.getName());
            viewName = i.getName();
            Float temp;
            temp = Float.parseFloat(i.getRating());

            // If the user does not initially enter a rating, don't show the rating on the list view
            if (temp != 0) {
                listRB.setRating(Float.parseFloat(i.getRating()));
                listRB.setIsIndicator(true);
            } else {
                listRB.setVisibility(View.GONE);
            }
        }
        return v;
    }
}


