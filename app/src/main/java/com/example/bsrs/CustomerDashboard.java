package com.example.bsrs;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class CustomerDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    NavigationView nav_View,nav_footer;
    Toolbar toolbar;
    TextView omang,customerName,username;
    Button  logout;
    RelativeLayout exit_app,settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dashboard);
        /*---------------------------------------------------Top Nav View-----------------------------------------------------*/
        drawer = findViewById(R.id.drawer_layout);
        nav_View = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        View header = nav_View.getHeaderView(0);
        omang = header.findViewById(R.id.omang);
        customerName = header.findViewById(R.id.fullname);
        username = header.findViewById(R.id.username);
        logout = header.findViewById(R.id.logout);
        /*---------------------------------------------------LoggingOut------------------------------------------------------*/
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logoutIntent = new Intent(CustomerDashboard.this,Login.class);
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
                customerSettingsDataReturner();
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
        showCustomerHeaderData();


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
        nav_View.setCheckedItem(R.id.home_nav);

        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);

                } else {
                    drawer.openDrawer(GravityCompat.START);
                    //showCustomerHeaderData();
                }
            }
        });

    }

    public  void showCustomerHeaderData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("Cusfullname");
        String usernameCus = intent.getStringExtra("Cususername");
        String omangCus = intent.getStringExtra("Cusid");

        username.setText(usernameCus);
        customerName.setText(name);
        omang.setText(omangCus);


    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            return;

        }
    }

    public void customerProfileDataReturner(){
        Intent profile_intent = new Intent(CustomerDashboard.this,CustomerProfile.class);
        Customer cust = new Customer();
        Intent intent = getIntent();
        String name = intent.getStringExtra("Cusfullname");
        String id = intent.getStringExtra("Cusid");
        String email = intent.getStringExtra("Cusemail");
        String address = intent.getStringExtra("Cusaddress");
        String gender = intent.getStringExtra("Cusgender");
        String phone = intent.getStringExtra("Cusphone");
        String user = intent.getStringExtra("Cususername");
        String booking = intent.getStringExtra("Cusbooking");

        cust.setFullname(name);
        cust.setGender(gender);
        cust.setAddress(address);
        cust.setEmail(email);
        cust.setId(id);
        cust.setPhone(phone);
        cust.setUsername(user);
        cust.setBooking(booking);
        profile_intent.putExtra("Cusfullname",cust.getFullname());
        profile_intent.putExtra("Cusid",cust.getId());
        profile_intent.putExtra("Cusemail",cust.getEmail());
        profile_intent.putExtra("Cusaddress",cust.getAddress());
        profile_intent.putExtra("Cusgender",cust.getGender());
        profile_intent.putExtra("Cusphone",cust.getPhone());
        profile_intent.putExtra("Cususername",cust.getUsername());
        profile_intent.putExtra("Cusbooking",cust.getBooking());

        startActivity(profile_intent);

    }

    public void customerSettingsDataReturner(){
        Intent profile_intent = new Intent(CustomerDashboard .this,Settings.class);
        Customer cust = new Customer();
        Intent intent = getIntent();
        String name = intent.getStringExtra("Cusfullname");
        String id = intent.getStringExtra("Cusid");
        String email = intent.getStringExtra("Cusemail");
        String address = intent.getStringExtra("Cusaddress");
        String gender = intent.getStringExtra("Cusgender");
        String phone = intent.getStringExtra("Cusphone");
        String user = intent.getStringExtra("Cususername");

        cust.setFullname(name);
        cust.setGender(gender);
        cust.setAddress(address);
        cust.setEmail(email);
        cust.setId(id);
        cust.setPhone(phone);
        cust.setUsername(user);
        profile_intent.putExtra("Cusfullname",cust.getFullname());
        profile_intent.putExtra("Cusid",cust.getId());
        profile_intent.putExtra("Cusemail",cust.getEmail());
        profile_intent.putExtra("Cusaddress",cust.getAddress());
        profile_intent.putExtra("Cusgender",cust.getGender());
        profile_intent.putExtra("Cusphone",cust.getPhone());
        profile_intent.putExtra("Cususername",cust.getUsername());

        startActivity(profile_intent);

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.home_nav:
                break;
            case R.id.profile_nav:
                customerProfileDataReturner();
                break;
            case R.id.notification_nav:
                break;
            case R.id.driver_nav:
                break;
            case R.id.buses_nav:
                break;
            case R.id.location_nav:
                break;
            case R.id.help_nav:
                break;

        }
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
    public  void viewBuses(View view){
        Intent customer_Bus_intent = new Intent(CustomerDashboard.this,CustomerBus.class);
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

        customer_Bus_intent.putExtra("Cusfullname",cust.getFullname());
        customer_Bus_intent.putExtra("Cusid",cust.getId());
        customer_Bus_intent.putExtra("Cusemail",cust.getEmail());
        customer_Bus_intent.putExtra("Cusaddress",cust.getAddress());
        customer_Bus_intent.putExtra("Cusgender",cust.getGender());
        customer_Bus_intent.putExtra("Cusphone",cust.getPhone());
        customer_Bus_intent.putExtra("Cususername",cust.getUsername());
        customer_Bus_intent.putExtra("Cusbooking_id",booking_id);
        customer_Bus_intent.putExtra("Cusrated",cus_Rate);
        customer_Bus_intent.putExtra("CusisBooked",cus_isBooked);

        startActivity(customer_Bus_intent);


    }
    public  void viewDrivers(View view){
        Intent customer_Drivers_intent = new Intent(CustomerDashboard.this,CustomerDrivers.class);
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

        customer_Drivers_intent.putExtra("Cusfullname",cust.getFullname());
        customer_Drivers_intent.putExtra("Cusid",cust.getId());
        customer_Drivers_intent.putExtra("Cusemail",cust.getEmail());
        customer_Drivers_intent.putExtra("Cusaddress",cust.getAddress());
        customer_Drivers_intent.putExtra("Cusgender",cust.getGender());
        customer_Drivers_intent.putExtra("Cusphone",cust.getPhone());
        customer_Drivers_intent.putExtra("Cususername",cust.getUsername());
        customer_Drivers_intent.putExtra("Cusbooking_id",booking_id);
        customer_Drivers_intent.putExtra("Cusrated",cus_Rate);
        customer_Drivers_intent.putExtra("CusisBooked",cus_isBooked);

        startActivity(customer_Drivers_intent);



    }
    public  void viewPlaces(View view){


    }
    public  void bookBuses(View view){
        Intent From_To_intent = new Intent(CustomerDashboard.this,From_To.class);
        Customer cust = new Customer();
        Intent intent = getIntent();
        String name = intent.getStringExtra("Cusfullname");
        String id = intent.getStringExtra("Cusid");
        String email = intent.getStringExtra("Cusemail");
        String address = intent.getStringExtra("Cusaddress");
        String gender = intent.getStringExtra("Cusgender");
        String phone = intent.getStringExtra("Cusphone");
        String user = intent.getStringExtra("Cususername");

        cust.setFullname(name);
        cust.setGender(gender);
        cust.setAddress(address);
        cust.setEmail(email);
        cust.setId(id);
        cust.setPhone(phone);
        cust.setUsername(user);
        From_To_intent.putExtra("Cusfullname",cust.getFullname());
        From_To_intent.putExtra("Cusid",cust.getId());
        From_To_intent.putExtra("Cusemail",cust.getEmail());
        From_To_intent.putExtra("Cusaddress",cust.getAddress());
        From_To_intent.putExtra("Cusgender",cust.getGender());
        From_To_intent.putExtra("Cusphone",cust.getPhone());
        From_To_intent.putExtra("Cususername",cust.getUsername());

        startActivity(From_To_intent);




    }
    public  void viewSchedule(View view){


    }
    public  void viewBookings(View view){


    }

}
