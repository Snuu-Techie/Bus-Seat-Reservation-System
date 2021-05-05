package com.example.bsrs;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CustomerPlaces extends AppCompatActivity {
    ListView mPlaces;

    private ArrayList<String> mRoutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_places);
        mPlaces = findViewById(R.id.places_list);

        populateListView();
    }

    private void populateListView() {
        mRoutes = new ArrayList<>();
        String town1 ="Gaborone";
        mRoutes.add(town1);
        String town2 ="Lobatse";
        mRoutes.add(town2);
        String town3 ="Francistown";
        mRoutes.add(town3);
        String town4 ="Mahalapye";
        mRoutes.add(town4);
        ArrayAdapter<String> routeArrayAdapterAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mRoutes);
        mPlaces.setAdapter(routeArrayAdapterAdapter);

    }


}
