package com.example.achristians.recipe_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class ListAdapter extends ArrayAdapter<Recipes> {
    private ArrayList<Recipes> listRecipe;
    FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference dref = mFirebaseDatabase.getReference();
    String nameInLV;
    int number;
    private ListAdapter adapter;

    public ListAdapter(Context context, int text, ArrayList<Recipes> listRecipe) {
        super(context, text, listRecipe);
        this.listRecipe = listRecipe;
    }

    public void refreshEvents(ArrayList<Recipes> listRecipe) {
        this.listRecipe.clear();
        this.listRecipe.addAll(listRecipe);
        notifyDataSetChanged();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_view, null);
        }

        Recipes i = listRecipe.get(position);
        if (listRecipe.get(position) != null) {
            TextView name = v.findViewById(R.id.name);
            //         TextView tvValue = v.findViewById(R.id.tvValue);
//            Button edit = v.findViewById(R.id.edit);
//             Button delete = v.findViewById(R.id.delete);
            nameInLV = i.getName();
            name.setText(nameInLV);
            number = position;



//            delete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    dref.addListenerForSingleValueEvent(
//                            new ValueEventListener() {
//                                @Override
//                                public void onDataChange(DataSnapshot dataSnapshot) {
//                                    for (DataSnapshot dsp : dataSnapshot.getChildren()) {
//                                        // dsp.getValue();
//                                        String nameFromDB = dsp.getKey();
//                                        if (nameFromDB.equals(nameInLV)) {
//                                            dsp.getRef().removeValue();
//                                            System.out.println("deleting" + nameFromDB);
////                                            Intent edit = new Intent(getActivity(), MainActivity.class);
////                                            startActivity(edit);
//                                        //  adapter.clear();
//                                            listRecipe.remove(number);
//                                        notifyDataSetChanged();
//                                        }
//                                    }
//
//                                }
//
//                                @Override
//                                public void onCancelled(DatabaseError databaseError) {
//
//                                }
//
//                            });
//
//                }
//
//          });
        }
        return v;
    }
}


