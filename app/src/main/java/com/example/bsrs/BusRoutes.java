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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class BusRoutes extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public static int bus_count;

    DrawerLayout drawer;
    NavigationView nav_View,nav_footer;
    Toolbar toolbar;
    TextView omang,adminName,username;
    Button logout;
    RelativeLayout exit_app,settings;
    ArrayList<Route> mRoutes;
    private Route_DisplayRecyclerAdapter adapter;
    private RecyclerView myRecycleView;
    private RecyclerView.LayoutManager mylayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_routes);

        drawer = findViewById(R.id.drawer_layout);
        nav_View = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        View header = nav_View.getHeaderView(0);
        omang = header.findViewById(R.id.omang);
        adminName = header.findViewById(R.id.fullname);
        username = header.findViewById(R.id.username);
        logout = header.findViewById(R.id.logout);

        initAllRoutes();
        /*---------------------------------------------------LoggingOut------------------------------------------------------*/
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logoutIntent = new Intent(BusRoutes.this,Login.class);
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

    private void initAllRoutes() {
        mRoutes = new ArrayList<>();
        /*----------------------  1st Object--------------------------------------------------*/
        Route myRoute1 = new Route();
        myRoute1.setFrom("Gaborone");
        myRoute1.setTo("Lobatse");
        myRoute1.setRoute_id("01");
        myRoute1.setCount(4);
        mRoutes.add(myRoute1);
        /*------------------------------2nd object------------------------------------------*/
        Route myRoute2 = new Route();
        myRoute2.setFrom("Lobatse");
        myRoute2.setTo("Gaborone");
        myRoute2.setRoute_id("02");
        myRoute2.setCount(2);
        mRoutes.add(myRoute2);
        /*-------------------------------3rd object-----------------------------------------*/
        Route myRoute3 = new Route();
        myRoute3.setFrom("Francistown");
        myRoute3.setTo("Lobatse");
        myRoute3.setRoute_id("03");
        myRoute3.setCount(2);
        mRoutes.add(myRoute3);
        /*-------------------------------4th object-----------------------------------------*/
        Route myRoute4 = new Route();
        myRoute4.setFrom("Gaborone");
        myRoute4.setTo("Mahalapye");
        myRoute4.setRoute_id("04");
        myRoute4.setCount(3);
        mRoutes.add(myRoute4);
        /*--------------------------------5th object----------------------------------------*/
        Route myRoute5 = new Route();
        myRoute5.setFrom("Mahalapye");
        myRoute5.setTo("Gaborone");
        myRoute5.setRoute_id("05");
        myRoute5.setCount(2);
        mRoutes.add(myRoute5);
        /*--------------------------------6th object----------------------------------------*/
        Route myRoute6 = new Route();
        myRoute6.setFrom("Gaborone");
        myRoute6.setTo("Francistown");
        myRoute6.setRoute_id("06");
        myRoute6.setCount(1);
        mRoutes.add(myRoute6);
        /*---------------------------------7th  object---------------------------------------*/
        Route myRoute7 = new Route();
        myRoute7.setFrom("Lobatse");
        myRoute7.setTo("Francistown");
        myRoute7.setRoute_id("07");
        myRoute7.setCount(1);
        mRoutes.add(myRoute7);
        /*---------------------------------8th  object---------------------------------------*/
        Route myRoute8 = new Route();
        myRoute8.setFrom("Francistown");
        myRoute8.setTo("Gaborone");
        myRoute8.setRoute_id("08");
        myRoute8.setCount(5);
        mRoutes.add(myRoute8);

        Log.d(TAG, String.valueOf(mRoutes.get(0)));

        myRecycleView = findViewById(R.id.adminRoutes_Recyclerview);
        myRecycleView.setHasFixedSize(true);
        mylayout = new LinearLayoutManager(BusRoutes.this);
        adapter  = new Route_DisplayRecyclerAdapter(mRoutes);
        myRecycleView.setLayoutManager(mylayout);
        myRecycleView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setOnitemClickListener(new Route_DisplayRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int postion) {
                returnRoutesById(postion);
            }
        });


    }
    public void returnRoutesById(int position){
        Route route = new Route();
        Administration admin = new Administration();
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
        /*---------------------------------Routes-------------------------------------------*/
        route.setRoute_id(mRoutes.get(position).getRoute_id());
        Intent AdminRoutesIntent= new Intent(BusRoutes.this,AdminDisplayBusByRoutes.class);
        AdminRoutesIntent.putExtra("Adfullname",admin.getFullname());
        AdminRoutesIntent.putExtra("Adid",admin.getId());
        AdminRoutesIntent.putExtra("Ademail",admin.getEmail());
        AdminRoutesIntent.putExtra("Adaddress",admin.getAddress());
        AdminRoutesIntent.putExtra("Adgender",admin.getGender());
        AdminRoutesIntent.putExtra("Adphone",admin.getPhone());
        AdminRoutesIntent.putExtra("Adusername",admin.getUsername());
        AdminRoutesIntent.putExtra("id",route.getRoute_id());
        startActivity(AdminRoutesIntent);

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
        Intent profile_intent = new Intent(BusRoutes.this,Settings.class);
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
        Intent profile_intent = new Intent(BusRoutes.this,AdminDashboard.class);
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
        Intent profile_intent = new Intent(BusRoutes.this,AdminCustomer.class);
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
        Intent profile_intent = new Intent(BusRoutes.this,AdminBus.class);
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
    public void adminProfileDataReturner(){
        Intent profile_intent = new Intent(BusRoutes.this,AdminProfile.class);
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

    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    Query newsRef = rootRef.child("Routes");

    public void availableBuses(int postion) {
        final Route route = new Route();
        route.setFrom(mRoutes.get(postion).getFrom());
        route.setTo(mRoutes.get(postion).getTo());

        final ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Route fireroute = new Route();

                    fireroute.setFrom(ds.child("from").getValue(String.class));
                    fireroute.setTo(ds.child("to").getValue(String.class));
                    if(fireroute.getFrom().equals(route.getFrom()) && fireroute.getTo().equals(route.getTo())){
                        ArrayList<String> routes = new ArrayList<>();
                        //fireroute.setRoute_id();
                        String bus_plate = fireroute.getBus_plate_number();
                        routes.add(bus_plate);
                        Log.d(TAG, String.valueOf(routes.get(0)));
                        Administration admin = new Administration();
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


                        Intent AdminRoutesIntent= new Intent(BusRoutes.this,AdminDisplayBusByRoutes.class);
                        AdminRoutesIntent.putStringArrayListExtra("routeArray",routes);
                        AdminRoutesIntent.putExtra("Adfullname",admin.getFullname());
                        AdminRoutesIntent.putExtra("Adid",admin.getId());
                        AdminRoutesIntent.putExtra("Ademail",admin.getEmail());
                        AdminRoutesIntent.putExtra("Adaddress",admin.getAddress());
                        AdminRoutesIntent.putExtra("Adgender",admin.getGender());
                        AdminRoutesIntent.putExtra("Adphone",admin.getPhone());
                        AdminRoutesIntent.putExtra("Adusername",admin.getUsername());
                        startActivity(AdminRoutesIntent);

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

}
