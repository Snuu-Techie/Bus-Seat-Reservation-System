package com.example.bsrs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class Available_Buses extends AppCompatActivity  {
    DrawerLayout drawer;
    NavigationView nav_View,nav_footer;
    Toolbar toolbar;
    TextView omang,adminName,username,toolbar_title;
    Button logout;
    private Query mNewsRef;
    private DatabaseReference mRootRef;
    RelativeLayout exit_app,settings;
    ArrayList<Bus> mBus;
    ArrayList<Route> mRoutes;
    ArrayList<Route> mFilteredRoutes;
    Button addDrivers;
    private Available_Bus_RecyclerViewAdapter adapter;
    private RecyclerView myRecycleView;
    private RecyclerView.LayoutManager mylayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available__buses);
        //driverArray();
        //recycler();
        initBuses();
        /*---------------------------------------------------------------Add Button---------------------------------------------------------------------*/


        toolbar = findViewById(R.id.toolbar);




    }

    public  void showAdminHeaderData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("Adfullname");
        String usernameAd = intent.getStringExtra("Adusername");
        String omangAd = intent.getStringExtra("Adid");

        username.setText(usernameAd);
        adminName.setText(name);
        omang.setText(omangAd);


    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();

        }
    }

    public void adminSettingsDataReturner(){
        Intent profile_intent = new Intent(Available_Buses.this,Settings.class);
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

    public void adminHomeDataReturner(){
        Intent profile_intent = new Intent(Available_Buses.this,AdminDashboard.class);
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


    public void initBuses(){
        mBus  = new ArrayList<>();
        Intent intent = getIntent();
        String route = intent.getStringExtra("id");
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mNewsRef = mRootRef.child("Bus").orderByChild("route_id").equalTo(route);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Bus myBus = new Bus();
                    //mydriver = ds.getValue(Driver.class);
                    //driver.add(mydriver);
                    myBus.setBus_plate_number(ds.child("bus_plate_number").getValue(String.class));
                    myBus.setSeats_Id(ds.child("seats_Id").getValue(String.class));
                    myBus.setNumber_of_seats(ds.child("number_of_seats").getValue(String.class));
                    myBus.setRoute_id(ds.child("route_id").getValue(String.class));
                    Log.d(TAG,myBus.getBus_plate_number());
                    myBus.setTimetable_id(ds.child("timetable_id").getValue(String.class));
                    myBus.setModel(ds.child("model").getValue(String.class));

                    mBus.add(myBus);

                    //driver.add(new Driver(mydriver.getFullname(),mydriver.getUsername(),mydriver.getEmail(),mydriver.getPhone(),mydriver.getAddress(),
                    //  mydriver.getPassword(),mydriver.getGender(),mydriver.getId(),mydriver.getBus_plate_number(),mydriver.getRating()));
                    Log.d(TAG, String.valueOf(mBus.get(0)));

                    myRecycleView = findViewById(R.id.available_buses_Recyclerview);
                    myRecycleView.setHasFixedSize(true);
                    mylayout = new LinearLayoutManager(Available_Buses.this);
                    adapter  = new Available_Bus_RecyclerViewAdapter(mBus);
                    myRecycleView.setLayoutManager(mylayout);
                    myRecycleView.setAdapter(adapter);

                    adapter.setOnitemClickListener(new Available_Bus_RecyclerViewAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int postion) {
                            busProfile(postion);
                        }
                    });



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, databaseError.getMessage());

            }
        };
        mNewsRef.addListenerForSingleValueEvent(valueEventListener);

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    public void busProfile(int position){
        Bus mybus = new Bus();
        Administration admin = new Administration();
        /*---------------------------------Driver--------------------------------------------*/
        mybus.setSeats_Id( mBus.get(position).getSeats_Id());
        mybus.setBus_plate_number( mBus.get(position).getBus_plate_number());
        mybus.setNumber_of_seats( mBus.get(position).getNumber_of_seats());
        Log.d(TAG,"RE FANO THLE SNUU " + mBus.get(0));
        mybus.setRoute_id( mBus.get(position).getRoute_id());
        mybus.setTimetable_id( mBus.get(position).getTimetable_id());
        mybus.setModel( mBus.get(position).getModel());

        /*---------------------------------Admin--------------------------------------------*/
        Intent intent = getIntent();
        String usernameCus = intent.getStringExtra("Cususername");
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


        Intent admin_driverIntent = new Intent(getApplicationContext(), Customer_Bus_Profile.class);
        /*---------------------------------Driver--------------------------------------------*/
        admin_driverIntent.putExtra("BusSeat_id", mybus.getSeats_Id());
        admin_driverIntent.putExtra("BusNumberSeat", mybus.getNumber_of_seats());
        admin_driverIntent.putExtra("BusModel", mybus.getModel());
        admin_driverIntent.putExtra("BusRoute", mybus.getRoute_id());
        admin_driverIntent.putExtra("BusBusPlate", mybus.getBus_plate_number());
        admin_driverIntent.putExtra("BusTime", mybus.getTimetable_id());

        /*---------------------------------Admin--------------------------------------------*/
        admin_driverIntent.putExtra("Adfullname",admin.getFullname());
        admin_driverIntent.putExtra("Adid",admin.getId());
        admin_driverIntent.putExtra("Ademail",admin.getEmail());
        admin_driverIntent.putExtra("Adaddress",admin.getAddress());
        admin_driverIntent.putExtra("Adgender",admin.getGender());
        admin_driverIntent.putExtra("Adphone",admin.getPhone());
        admin_driverIntent.putExtra("Adusername",admin.getUsername());
        admin_driverIntent.putExtra("Cususername",usernameCus);


        startActivity(admin_driverIntent);

    }

   /* public void driverArray(){
        driver = initAllDrivers();
    }*/

    public void adminProfileDataReturner(){
        Intent profile_intent = new Intent(Available_Buses.this,AdminProfile.class);
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


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflate = getMenuInflater();
        inflate.inflate(R.menu.search_menu,menu);

        MenuItem search_item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) search_item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
