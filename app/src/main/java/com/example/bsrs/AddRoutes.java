package com.example.bsrs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddRoutes extends AppCompatActivity {
    TextInputLayout route_id,to,from,busPlate;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_routes);

        route_id = findViewById(R.id.regroute_id);
        to = findViewById(R.id.regTo);
        from = findViewById(R.id.regFrom);
        busPlate = findViewById(R.id.regBusid);
        Intent intent = getIntent();
        String intentBus =intent.getStringExtra("BusBusPlate");
        busPlate.getEditText().setText(intentBus);

        /*---------------------------------------------------Top Nav Bar data------------------------------------------------------*/

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.back_button);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   adminRouteDataReturner();

            }
        });

    }

    public void addRoute(View view){
        /*if(!validateName() | !validatePassword() | !validatePhonenumber() | !validateEmail() | !validateUsername() | !validateAddress()){
            return;
        }*/
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Routes");


        //customer.setFullname
        String myRoute  = (route_id.getEditText().getText().toString());
        // customer.setUsername
        String myTo = (to.getEditText().getText().toString());
        //customer.setEmail
        String myFrom =  (from.getEditText().getText().toString());
        //customer.setPhone
        String myBusPlate=  (busPlate.getEditText().getText().toString());
        //customer.setAddress


        Route route = new Route(myRoute,myBusPlate,myFrom,myTo);

        reference.child(myRoute).setValue(route);
        Toast.makeText(this,"New Route Added",Toast.LENGTH_LONG).show();

    }

    public void adminRouteDataReturner(){
        Intent admin_Route_intent = new Intent(AddRoutes.this,BusProfile.class);
        Administration admin = new Administration();
        Intent intent = getIntent();
        String name = intent.getStringExtra("Adfullname");
        String id = intent.getStringExtra("Adid");
        String email = intent.getStringExtra("Ademail");
        String address = intent.getStringExtra("Adaddress");
        String gender = intent.getStringExtra("Adgender");
        String phone = intent.getStringExtra("Adphone");
        String user = intent.getStringExtra("Adusername");

        String bus_model = intent.getStringExtra("BusModel");
        String bus_seat_id = intent.getStringExtra("BusSeat_id");
        String bus_plate= intent.getStringExtra("BusBusPlate");
        String bus_route_id = intent.getStringExtra("BusRoute");
        String bus_timetable_id = intent.getStringExtra("BusTime");
        String bus_number_of_seat = intent.getStringExtra("BusNumberSeat");

        admin_Route_intent.putExtra("BusSeat_id", bus_seat_id);
        admin_Route_intent.putExtra("BusNumberSeat", bus_number_of_seat);
        admin_Route_intent.putExtra("BusModel", bus_model);
        admin_Route_intent.putExtra("BusRoute", bus_route_id);
        admin_Route_intent.putExtra("BusBusPlate", bus_plate);
        admin_Route_intent.putExtra("BusTime", bus_timetable_id);

        admin.setFullname(name);
        admin.setGender(gender);
        admin.setAddress(address);
        admin.setEmail(email);
        admin.setId(id);
        admin.setPhone(phone);
        admin.setUsername(user);
        admin_Route_intent.putExtra("Adfullname",admin.getFullname());
        admin_Route_intent.putExtra("Adid",admin.getId());
        admin_Route_intent.putExtra("Ademail",admin.getEmail());
        admin_Route_intent.putExtra("Adaddress",admin.getAddress());
        admin_Route_intent.putExtra("Adgender",admin.getGender());
        admin_Route_intent.putExtra("Adphone",admin.getPhone());
        admin_Route_intent.putExtra("Adusername",admin.getUsername());
        startActivity(admin_Route_intent);

    }

}
