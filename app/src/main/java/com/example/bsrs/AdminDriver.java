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

public class AdminDriver extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    NavigationView nav_View,nav_footer;
    Toolbar toolbar;
    TextView omang,adminName,username;
    Button logout;
    RelativeLayout exit_app,settings;
    ArrayList<Driver> driver;
    Button addDrivers;
    private AdminDriver_RecyclerViewAdapter adapter;
    private RecyclerView myRecycleView;
    private RecyclerView.LayoutManager mylayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_driver);
        //driverArray();
        //recycler();
        initAllDrivers();
        /*---------------------------------------------------------------Add Button---------------------------------------------------------------------*/
        addDrivers = findViewById(R.id.add_drivers );
        addDrivers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addDrivers = new Intent(AdminDriver.this,CreateDriverAccounts.class);
                startActivity(addDrivers);
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
                Intent logoutIntent = new Intent(AdminDriver.this,Login.class);
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
        Intent profile_intent = new Intent(AdminDriver.this,Settings.class);
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
        Intent profile_intent = new Intent(AdminDriver.this,AdminDashboard.class);
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

    public void adminCustomerDataReturner(){
        Intent profile_intent = new Intent(AdminDriver.this,AdminCustomer.class);
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

    public void adminBusDataReturner(){
        Intent profile_intent = new Intent(AdminDriver.this,AdminBus.class);
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
               adminBusDataReturner();
                break;
            case R.id.admin_customer_nav:
                adminCustomerDataReturner();
                break;
            case R.id.admin_help_nav:
                break;

        }
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    private  void initAllDrivers(){
        //TODO:Initialise all drivers
         driver = new ArrayList<>();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference newsRef = rootRef.child("Driver");
        final ValueEventListener valueEventListener = new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Driver mydriver = new Driver();
                    //mydriver = ds.getValue(Driver.class);
                    //driver.add(mydriver);
                    mydriver.setFullname(ds.child("fullname").getValue(String.class));
                    mydriver.setGender(ds.child("gender").getValue(String.class));
                    mydriver.setBus_plate_number(ds.child("bus_plate_number").getValue(String.class));
                    mydriver.setId(ds.child("license_No").getValue(String.class));
                    Log.d(TAG,mydriver.getId());
                    mydriver.setRating(ds.child("rating").getValue(String.class));
                    mydriver.setAddress(ds.child("address").getValue(String.class));
                    mydriver.setEmail(ds.child("email").getValue(String.class));
                    mydriver.setPhone(ds.child("phone").getValue(String.class));
                    mydriver.setPassword(ds.child("password").getValue(String.class));
                    mydriver.setUsername(ds.child("username").getValue(String.class));
                    driver.add(mydriver);

                    //driver.add(new Driver(mydriver.getFullname(),mydriver.getUsername(),mydriver.getEmail(),mydriver.getPhone(),mydriver.getAddress(),
                      //  mydriver.getPassword(),mydriver.getGender(),mydriver.getId(),mydriver.getBus_plate_number(),mydriver.getRating()));
                    Log.d(TAG, String.valueOf(driver.get(0)));

                    myRecycleView = findViewById(R.id.adminDriver_Recyclerview);
                    myRecycleView.setHasFixedSize(true);
                    mylayout = new LinearLayoutManager(AdminDriver.this);
                    adapter  = new AdminDriver_RecyclerViewAdapter(driver);
                    myRecycleView.setLayoutManager(mylayout);
                    myRecycleView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    adapter.setOnitemClickListener(new AdminDriver_RecyclerViewAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int postion) {
                            driverProfile(postion);
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
    /*public void recycler(){



    }*/

    public void driverProfile(int position){
        Driver mydriver = new Driver();
        Administration admin = new Administration();
        /*---------------------------------Driver--------------------------------------------*/
        mydriver.setUsername( driver.get(position).getUsername());
        mydriver.setBus_plate_number( driver.get(position).getBus_plate_number());
        mydriver.setId( driver.get(position).getId());
        Log.d(TAG,"RE FANO THLE SNUU " + driver.get(0));
        mydriver.setRating( driver.get(position).getRating());
        mydriver.setAddress( driver.get(position).getAddress());
        mydriver.setEmail( driver.get(position).getEmail());
        mydriver.setFullname( driver.get(position).getFullname());
        mydriver.setGender( driver.get(position).getGender());
        mydriver.setPassword( driver.get(position).getPassword());
        mydriver.setPhone( driver.get(position).getPhone());
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


        Intent admin_driverIntent = new Intent(getApplicationContext(), Admin_DriverProfiles.class);
        /*---------------------------------Driver--------------------------------------------*/
        admin_driverIntent.putExtra("Drfullname", mydriver.getFullname());
        admin_driverIntent.putExtra("Drid", mydriver.getId());
        admin_driverIntent.putExtra("Dremail", mydriver.getEmail());
        admin_driverIntent.putExtra("Draddress", mydriver.getAddress());
        admin_driverIntent.putExtra("DrbusPlateNo", mydriver.getBus_plate_number());
        admin_driverIntent.putExtra("Drphone", mydriver.getPhone());
        admin_driverIntent.putExtra("Drusername", mydriver.getUsername());
        admin_driverIntent.putExtra("Drrating", mydriver.getRating());
        admin_driverIntent.putExtra("Drgender", mydriver.getGender());
        admin_driverIntent.putExtra("Drpassword", mydriver.getPassword());
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
        Intent profile_intent = new Intent(AdminDriver.this,AdminProfile.class);
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
}
