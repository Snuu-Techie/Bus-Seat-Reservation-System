package com.example.bsrs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class BusProfile extends AppCompatActivity {

    Toolbar toolbar;
    TextView model,plate_bus,driver_id,driver_fullname ;
    Button route, timetable,view_driver;
    ArrayList<Driver> drivers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_profile_activity);


        /*---------------------------------------------------profile data------------------------------------------------------*/
        model = findViewById(R.id.bus_model);
        plate_bus = findViewById(R.id.bus_plate);
        driver_id = findViewById(R.id.drivers_license);
        driver_fullname = findViewById(R.id.drivers_name);
        route = findViewById(R.id.add_route);
        timetable = findViewById(R.id.add_time);
        view_driver = findViewById(R.id.view_driver);


        /*---------------------------------------------------Show profile data------------------------------------------------------*/
        showProfileData();

        /*---------------------------------------------------Top Nav Bar data------------------------------------------------------*/

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.back_icon_blue);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminBusDataReturner();
            }
        });

    }

    private  void showProfileData(){
        final Driver driver = new Driver();
        Intent intent = getIntent();
        String bus_model = intent.getStringExtra("BusModel");
        String bus_seat_id = intent.getStringExtra("BusSeat_id");


        final String busBusPlate = intent.getStringExtra("BusBusPlate");


        model.setText(bus_model);
        plate_bus.setText(busBusPlate);


        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference newsRef = rootRef.child("Driver");
        drivers = new ArrayList<>();

        final ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    driver.setBus_plate_number(ds.child("bus_plate_number").getValue(String.class));
                    if(driver.getBus_plate_number().equals(busBusPlate)){
                        driver.setFullname(ds.child("fullname").getValue(String.class));
                        driver_id.setText(driver.getFullname());
                        driver.setGender(ds.child("gender").getValue(String.class));
                        driver.setBus_plate_number(ds.child("bus_plate_number").getValue(String.class));
                        driver.setId(ds.child("license_No").getValue(String.class));
                        driver_fullname.setText(driver.getId());
                        Log.d(TAG,"RE FANO GAPE"+ driver.getId());
                        driver.setRating(ds.child("rating").getValue(String.class));
                        driver.setAddress(ds.child("address").getValue(String.class));
                        driver.setEmail(ds.child("email").getValue(String.class));
                        driver.setPhone(ds.child("phone").getValue(String.class));
                        driver.setPassword(ds.child("password").getValue(String.class));
                        driver.setUsername(ds.child("username").getValue(String.class));
                        drivers.add(driver);
                        Log.d(TAG, String.valueOf(drivers.get(0)));

                        view_driver.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent driverIntent = new Intent(getApplicationContext(),Bus_DriverProfile.class);
                                driverIntent.putExtra("Drfullname", driver.getFullname());
                                driverIntent.putExtra("Drid", driver.getId());
                                driverIntent.putExtra("Dremail", driver.getEmail());
                                driverIntent.putExtra("Draddress", driver.getAddress());
                                driverIntent.putExtra("DrbusPlateNo", driver.getBus_plate_number());
                                driverIntent.putExtra("Drphone", driver.getPhone());
                                driverIntent.putExtra("Drusername", driver.getUsername());
                                driverIntent.putExtra("Drrating", driver.getRating());
                                driverIntent.putExtra("Drgender", driver.getGender());
                                driverIntent.putExtra("Drpassword", driver.getPassword());
                                
                                Intent intentdata = getIntent();
                                String bus_model = intentdata.getStringExtra("BusModel");
                                String bus_seat_id = intentdata.getStringExtra("BusSeat_id");
                                String bus_plate= intentdata.getStringExtra("BusBusPlate");
                                String bus_route_id = intentdata.getStringExtra("BusRoute");
                                String bus_timetable_id = intentdata.getStringExtra("BusTime");
                                String bus_number_of_seat = intentdata.getStringExtra("BusNumberSeat");

                                driverIntent.putExtra("BusSeat_id", bus_seat_id);
                                driverIntent.putExtra("BusNumberSeat", bus_number_of_seat);
                                driverIntent.putExtra("BusModel", bus_model);
                                driverIntent.putExtra("BusRoute", bus_route_id);
                                driverIntent.putExtra("BusBusPlate", bus_plate);
                                driverIntent.putExtra("BusTime", bus_timetable_id);
                                
                                String name = intentdata.getStringExtra("Adfullname");
                                String id = intentdata.getStringExtra("Adid");
                                String email = intentdata.getStringExtra("Ademail");
                                String address = intentdata.getStringExtra("Adaddress");
                                String gender = intentdata.getStringExtra("Adgender");
                                String phone = intentdata.getStringExtra("Adphone");
                                String user = intentdata.getStringExtra("Adusername");

                                driverIntent.putExtra("Adfullname",name);
                                driverIntent.putExtra("Adid",id);
                                driverIntent.putExtra("Ademail",email);
                                driverIntent.putExtra("Adaddress",address);
                                driverIntent.putExtra("Adgender",gender);
                                driverIntent.putExtra("Adphone",phone);
                                driverIntent.putExtra("Adusername",user);
                                
                                
                                
                                
                                
                                startActivity(driverIntent);
                            }
                        });

                        route.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent routeIntent = new Intent(getApplicationContext(),AddRoutes.class);
                                routeIntent.putExtra("Drfullname", driver.getFullname());
                                routeIntent.putExtra("Drid", driver.getId());
                                routeIntent.putExtra("Dremail", driver.getEmail());
                                routeIntent.putExtra("Draddress", driver.getAddress());
                                routeIntent.putExtra("DrbusPlateNo", driver.getBus_plate_number());
                                routeIntent.putExtra("Drphone", driver.getPhone());
                                routeIntent.putExtra("Drusername", driver.getUsername());
                                routeIntent.putExtra("Drrating", driver.getRating());
                                routeIntent.putExtra("Drgender", driver.getGender());
                                routeIntent.putExtra("Drpassword", driver.getPassword());

                                Intent intentdata = getIntent();
                                String bus_model = intentdata.getStringExtra("BusModel");
                                String bus_seat_id = intentdata.getStringExtra("BusSeat_id");
                                String bus_plate= intentdata.getStringExtra("BusBusPlate");
                                String bus_route_id = intentdata.getStringExtra("BusRoute");
                                String bus_timetable_id = intentdata.getStringExtra("BusTime");
                                String bus_number_of_seat = intentdata.getStringExtra("BusNumberSeat");

                                routeIntent.putExtra("BusSeat_id", bus_seat_id);
                                routeIntent.putExtra("BusNumberSeat", bus_number_of_seat);
                                routeIntent.putExtra("BusModel", bus_model);
                                routeIntent.putExtra("BusRoute", bus_route_id);
                                routeIntent.putExtra("BusBusPlate", bus_plate);
                                routeIntent.putExtra("BusTime", bus_timetable_id);

                                String name = intentdata.getStringExtra("Adfullname");
                                String id = intentdata.getStringExtra("Adid");
                                String email = intentdata.getStringExtra("Ademail");
                                String address = intentdata.getStringExtra("Adaddress");
                                String gender = intentdata.getStringExtra("Adgender");
                                String phone = intentdata.getStringExtra("Adphone");
                                String user = intentdata.getStringExtra("Adusername");

                                routeIntent.putExtra("Adfullname",name);
                                routeIntent.putExtra("Adid",id);
                                routeIntent.putExtra("Ademail",email);
                                routeIntent.putExtra("Adaddress",address);
                                routeIntent.putExtra("Adgender",gender);
                                routeIntent.putExtra("Adphone",phone);
                                routeIntent.putExtra("Adusername",user);





                                startActivity(routeIntent);
                            }
                        });

                        timetable.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent timetableIntent = new Intent(getApplicationContext(),AddTimetable.class);
                                timetableIntent.putExtra("Drfullname", driver.getFullname());
                                timetableIntent.putExtra("Drid", driver.getId());
                                timetableIntent.putExtra("Dremail", driver.getEmail());
                                timetableIntent.putExtra("Draddress", driver.getAddress());
                                timetableIntent.putExtra("DrbusPlateNo", driver.getBus_plate_number());
                                timetableIntent.putExtra("Drphone", driver.getPhone());
                                timetableIntent.putExtra("Drusername", driver.getUsername());
                                timetableIntent.putExtra("Drrating", driver.getRating());
                                timetableIntent.putExtra("Drgender", driver.getGender());
                                timetableIntent.putExtra("Drpassword", driver.getPassword());

                                Intent intentdata = getIntent();
                                String bus_model = intentdata.getStringExtra("BusModel");
                                String bus_seat_id = intentdata.getStringExtra("BusSeat_id");
                                String bus_plate= intentdata.getStringExtra("BusBusPlate");
                                String bus_route_id = intentdata.getStringExtra("BusRoute");
                                String bus_timetable_id = intentdata.getStringExtra("BusTime");
                                String bus_number_of_seat = intentdata.getStringExtra("BusNumberSeat");

                                timetableIntent.putExtra("BusSeat_id", bus_seat_id);
                                timetableIntent.putExtra("BusNumberSeat", bus_number_of_seat);
                                timetableIntent.putExtra("BusModel", bus_model);
                                timetableIntent.putExtra("BusRoute", bus_route_id);
                                timetableIntent.putExtra("BusBusPlate", bus_plate);
                                timetableIntent.putExtra("BusTime", bus_timetable_id);

                                String name = intentdata.getStringExtra("Adfullname");
                                String id = intentdata.getStringExtra("Adid");
                                String email = intentdata.getStringExtra("Ademail");
                                String address = intentdata.getStringExtra("Adaddress");
                                String gender = intentdata.getStringExtra("Adgender");
                                String phone = intentdata.getStringExtra("Adphone");
                                String user = intentdata.getStringExtra("Adusername");

                                timetableIntent.putExtra("Adfullname",name);
                                timetableIntent.putExtra("Adid",id);
                                timetableIntent.putExtra("Ademail",email);
                                timetableIntent.putExtra("Adaddress",address);
                                timetableIntent.putExtra("Adgender",gender);
                                timetableIntent.putExtra("Adphone",phone);
                                timetableIntent.putExtra("Adusername",user);





                                startActivity(timetableIntent);

                            }
                        });

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, databaseError.getMessage());
            }
        };

        newsRef.addListenerForSingleValueEvent(valueEventListener);
    }

    public void adminEditAdminDataReturner(){
        Intent profile_intent = new Intent(BusProfile.this,EditAdminProfile.class);
        Customer cust = new Customer();
        Intent intent = getIntent();
        String name = intent.getStringExtra("Adfullname");
        String id = intent.getStringExtra("Adid");
        String email = intent.getStringExtra("Ademail");
        String address = intent.getStringExtra("Adaddress");
        String gender = intent.getStringExtra("Adgender");
        String phone = intent.getStringExtra("Adphone");
        String user = intent.getStringExtra("Adusername");

        cust.setFullname(name);
        cust.setGender(gender);
        cust.setAddress(address);
        cust.setEmail(email);
        cust.setId(id);
        cust.setPhone(phone);
        cust.setUsername(user);
        profile_intent.putExtra("Adfullname",cust.getFullname());
        profile_intent.putExtra("Adid",cust.getId());
        profile_intent.putExtra("Ademail",cust.getEmail());
        profile_intent.putExtra("Adaddress",cust.getAddress());
        profile_intent.putExtra("Adgender",cust.getGender());
        profile_intent.putExtra("Adphone",cust.getPhone());
        profile_intent.putExtra("Adusername",cust.getUsername());

        startActivity(profile_intent);

    }
    public void adminProfileDataReturner(){
        Intent profile_intent = new Intent(BusProfile.this,AdminProfile.class);
        Administration admin = new Administration();
        Intent intent = getIntent();
        String name = intent.getStringExtra("Adfullname");
        String id = intent.getStringExtra("Adid");
        String email = intent.getStringExtra("Ademail");
        String address = intent.getStringExtra("Adaddress");
        String gender = intent.getStringExtra("Adgender");
        String phone = intent.getStringExtra("Adphone");
        String user = intent.getStringExtra("Adusername");

        admin.setFullname(name);
        admin.setGender(gender);
        admin.setAddress(address);
        admin.setEmail(email);
        admin.setId(id);
        admin.setPhone(phone);
        admin.setUsername(user);
        profile_intent.putExtra("Adfullname",admin.getFullname());
        profile_intent.putExtra("Adid",admin.getId());
        profile_intent.putExtra("Ademail",admin.getEmail());
        profile_intent.putExtra("Adaddress",admin.getAddress());
        profile_intent.putExtra("Adgender",admin.getGender());
        profile_intent.putExtra("Adphone",admin.getPhone());
        profile_intent.putExtra("Adusername",admin.getUsername());

        startActivity(profile_intent);

    }



    public void callEditBusListener(View view){
        Intent intent = getIntent();
        String bus_model = intent.getStringExtra("BusModel");
        String bus_seat_id = intent.getStringExtra("BusSeat_id");
        String bus_plate= intent.getStringExtra("BusBusPlate");
        String bus_route_id = intent.getStringExtra("BusRoute");
        String bus_timetable_id = intent.getStringExtra("BusTime");
        String bus_number_of_seat = intent.getStringExtra("BusNumberSeat");

        Intent busIntentEditor = new Intent(getApplicationContext(),EditBusDetails.class);

        busIntentEditor.putExtra("BusSeat_id", bus_seat_id);
        busIntentEditor.putExtra("BusNumberSeat", bus_number_of_seat);
        busIntentEditor.putExtra("BusModel", bus_model);
        busIntentEditor.putExtra("BusRoute", bus_route_id);
        busIntentEditor.putExtra("BusBusPlate", bus_plate);
        busIntentEditor.putExtra("BusTime", bus_timetable_id);

        startActivity(busIntentEditor);


    }
    public void adminBusDataReturner(){
        Intent admin_Driver_intent = new Intent(BusProfile.this,AdminBus.class);
        Administration admin = new Administration();
        Intent intent = getIntent();
        String name = intent.getStringExtra("Adfullname");
        String id = intent.getStringExtra("Adid");
        String email = intent.getStringExtra("Ademail");
        String address = intent.getStringExtra("Adaddress");
        String gender = intent.getStringExtra("Adgender");
        String phone = intent.getStringExtra("Adphone");
        String user = intent.getStringExtra("Adusername");

        admin.setFullname(name);
        admin.setGender(gender);
        admin.setAddress(address);
        admin.setEmail(email);
        admin.setId(id);
        admin.setPhone(phone);
        admin.setUsername(user);
        admin_Driver_intent.putExtra("Adfullname",admin.getFullname());
        admin_Driver_intent.putExtra("Adid",admin.getId());
        admin_Driver_intent.putExtra("Ademail",admin.getEmail());
        admin_Driver_intent.putExtra("Adaddress",admin.getAddress());
        admin_Driver_intent.putExtra("Adgender",admin.getGender());
        admin_Driver_intent.putExtra("Adphone",admin.getPhone());
        admin_Driver_intent.putExtra("Adusername",admin.getUsername());
        startActivity(admin_Driver_intent);

    }

    public void callEditCustomerProfileListener(View view) {
        Toast.makeText(this,"Editing of customers data is prohibited",Toast.LENGTH_LONG).show();
    }
    public void adminEditDriverDataReturner(){
        Intent profile_intent = new Intent(BusProfile.this,EditDriverProfile.class);
        Driver driver = new Driver();
        Intent intent = getIntent();
        String name = intent.getStringExtra("Drfullname");
        String id = intent.getStringExtra("Drid");
        String email = intent.getStringExtra("Dremail");
        String address = intent.getStringExtra("Draddress");
        String gender = intent.getStringExtra("Drgender");
        String phone = intent.getStringExtra("Drphone");
        String user = intent.getStringExtra("Drusername");
        String  busPlate= intent.getStringExtra("DrbusPlateNo");
        String rating = intent.getStringExtra("Drrating");


        driver.setFullname(name);
        driver.setGender(gender);
        driver.setAddress(address);
        driver.setEmail(email);
        driver.setId(id);
        driver.setPhone(phone);
        driver.setUsername(user);
        driver.setBus_plate_number(busPlate);
        driver.setRating(rating);

        profile_intent.putExtra("Drfullname",driver.getFullname());
        profile_intent.putExtra("Drid",driver.getId());
        profile_intent.putExtra("Dremail",driver.getEmail());
        profile_intent.putExtra("Draddress",driver.getAddress());
        profile_intent.putExtra("Drgender",driver.getGender());
        profile_intent.putExtra("Drphone",driver.getPhone());
        profile_intent.putExtra("Drusername",driver.getUsername());
        profile_intent.putExtra("DrbusPlateNo",driver.getBus_plate_number());
        profile_intent.putExtra("Drrating",driver.getRating());


        startActivity(profile_intent);

        //TODO add delete button,add edit button,fix the arraylist driver,be able to see the driver

    }



}

