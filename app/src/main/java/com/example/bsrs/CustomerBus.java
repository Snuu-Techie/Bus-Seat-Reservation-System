package com.example.bsrs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class CustomerBus extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    NavigationView nav_View,nav_footer;
    Toolbar toolbar;
    TextView omang,adminName,username,toolbar_title;
    Button logout;
    RelativeLayout exit_app,settings;
    ArrayList<Bus> bus;
    Button addDrivers;
    private Bus_RecyclerViewAdapter adapter;
    private RecyclerView myRecycleView;
    private RecyclerView.LayoutManager mylayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_driver);
        initAllBuses();
        /*---------------------------------------------------------------Add Button---------------------------------------------------------------------*/
        addDrivers = findViewById(R.id.add_drivers );
        addDrivers.setText("My Bus");
        addDrivers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myBus();
            }
        });

        drawer = findViewById(R.id.drawer_layout);
        nav_View = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        View header = nav_View.getHeaderView(0);
        omang = header.findViewById(R.id.omang);
        adminName = header.findViewById(R.id.fullname);
        username = header.findViewById(R.id.username);
        logout = header.findViewById(R.id.logout);
        /*---------------------------------------------------LoggingOut------------------------------------------------------*/
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logoutIntent = new Intent(CustomerBus.this,Login.class);
                startActivity(logoutIntent);
            }
        });

        /*---------------------------------------------------Below Nav Viw------------------------------------------------------*/
        nav_footer = findViewById(R.id.nav_footer);
        View header_footer = nav_footer.getHeaderView(0);
        exit_app = header_footer.findViewById(R.id.exit_layout);
        settings = header_footer.findViewById(R.id.settings_layout);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminSettingsDataReturner();
            }
        });


        exit_app.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
                moveTaskToBack(true);
            }
        });

        /*---------------------------------------------------Display data------------------------------------------------------*/
        showAdminHeaderData();


        /*---------------------------------------------------Toolbar actions------------------------------------------------------*/
        setSupportActionBar(toolbar);
        toolbar_title = toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setText("Buses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        /*---------------------------------------------------Navigation Drawer actions------------------------------------------------------*/

        nav_View.bringToFront();
        nav_footer.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.menu);
        toggle.syncState();

        nav_View.setNavigationItemSelectedListener(this);
        nav_View.setCheckedItem(R.id.admin_driver_nav);

        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);

                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

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
        Intent profile_intent = new Intent(CustomerBus.this,Settings.class);
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
        Intent profile_intent = new Intent(CustomerBus.this,AdminDashboard.class);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.admin_home_nav:
                adminHomeDataReturner();
                break;
            case R.id.admin_profile_nav:
                adminProfileDataReturner();
                break;
            case R.id.admin_notification_nav:
                break;
            case R.id.admin_driver_nav:
                break;
            case R.id.admin_buses_nav:
                break;
            case R.id.admin_customer_nav:
                break;
            case R.id.admin_help_nav:
                break;

        }
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    private  void initAllBuses(){
        //TODO:Initialise all drivers
        bus = new ArrayList<>();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference newsRef = rootRef.child("Bus");
        final ValueEventListener valueEventListener = new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
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

                    bus.add(myBus);

                    //driver.add(new Driver(mydriver.getFullname(),mydriver.getUsername(),mydriver.getEmail(),mydriver.getPhone(),mydriver.getAddress(),
                    //  mydriver.getPassword(),mydriver.getGender(),mydriver.getId(),mydriver.getBus_plate_number(),mydriver.getRating()));
                    Log.d(TAG, String.valueOf(bus.get(0)));

                    myRecycleView = findViewById(R.id.adminDriver_Recyclerview);
                    myRecycleView.setHasFixedSize(true);
                    mylayout = new LinearLayoutManager(CustomerBus.this);
                    adapter  = new Bus_RecyclerViewAdapter(bus);
                    myRecycleView.setLayoutManager(mylayout);
                    myRecycleView.setAdapter(adapter);

                    adapter.setOnitemClickListener(new Bus_RecyclerViewAdapter.OnItemClickListener() {
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


        newsRef.addListenerForSingleValueEvent(valueEventListener);

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    /*public void recycler(){



    }*/

    public void busProfile(int position){
        Bus mybus = new Bus();
        Administration admin = new Administration();
        /*---------------------------------Driver--------------------------------------------*/
        mybus.setSeats_Id( bus.get(position).getSeats_Id());
        mybus.setBus_plate_number( bus.get(position).getBus_plate_number());
        mybus.setNumber_of_seats( bus.get(position).getNumber_of_seats());
        Log.d(TAG,"RE FANO THLE SNUU " + bus.get(0));
        mybus.setRoute_id( bus.get(position).getRoute_id());
        mybus.setTimetable_id( bus.get(position).getTimetable_id());
        mybus.setModel( bus.get(position).getModel());

        /*---------------------------------Admin--------------------------------------------*/
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


        Intent admin_driverIntent = new Intent(getApplicationContext(), BusProfile.class);
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


        startActivity(admin_driverIntent);

    }

   /* public void driverArray(){
        driver = initAllDrivers();
    }*/

    public void adminProfileDataReturner(){
        Intent profile_intent = new Intent(CustomerBus.this,AdminProfile.class);
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
        //driverArray();
        //recycler();

    }
    public void myBus(){
        Intent my_Bus_Intent = new Intent(CustomerBus.this,My_Bus.class);
        Customer cust = new Customer();
        Intent intent = getIntent();
        String name = intent.getStringExtra("Cusfullname");
        String id = intent.getStringExtra("Cusid");
        String email = intent.getStringExtra("Cusemail");
        String address = intent.getStringExtra("Cusaddress");
        String gender = intent.getStringExtra("Cusgender");
        String phone = intent.getStringExtra("Cusphone");
        String user = intent.getStringExtra("Cususername");
        String booking_id = intent.getStringExtra("Cusbooking_id");
        boolean cus_Rate = intent.getBooleanExtra("Cusrated",false);
        boolean cus_isBooked = intent.getBooleanExtra("CusisBooked",false);

        cust.setFullname(name);
        cust.setGender(gender);
        cust.setAddress(address);
        cust.setEmail(email);
        cust.setId(id);
        cust.setPhone(phone);
        cust.setUsername(user);

        my_Bus_Intent.putExtra("Cusfullname",cust.getFullname());
        my_Bus_Intent.putExtra("Cusid",cust.getId());
        my_Bus_Intent.putExtra("Cusemail",cust.getEmail());
        my_Bus_Intent.putExtra("Cusaddress",cust.getAddress());
        my_Bus_Intent.putExtra("Cusgender",cust.getGender());
        my_Bus_Intent.putExtra("Cusphone",cust.getPhone());
        my_Bus_Intent.putExtra("Cususername",cust.getUsername());
        my_Bus_Intent.putExtra("Cusbooking_id",booking_id);
        my_Bus_Intent.putExtra("Cusrated",cus_Rate);
        my_Bus_Intent.putExtra("CusisBooked",cus_isBooked);
        startActivity(my_Bus_Intent);


    }
}

