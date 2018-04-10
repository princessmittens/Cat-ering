package com.example.achristians.recipe_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
import com.example.achristians.asgn4.R;
import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Recipes> {
    //Declare variables
    private ArrayList<Recipes> listRecipe;
    TextView name;
    RatingBar listRB;

     // Local constructor for Array Adapter that takes the list of recipes from MainActivity and
     // populates the list view
    public ListAdapter(Context context, int text, ArrayList<Recipes> listRecipe) {
        super(context, text, listRecipe);
        this.listRecipe = listRecipe;
    }

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

            //Gets the text from the getter and assigns it to the textview
            name.setText(i.getName());

            //Get rating from getter
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


