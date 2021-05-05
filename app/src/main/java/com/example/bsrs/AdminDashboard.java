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
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class AdminDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
        DrawerLayout drawer;
        NavigationView nav_View,nav_footer;
        Toolbar toolbar;
        TextView omang,adminName,username;
        Button  logout;
        RelativeLayout exit_app,settings;
        CardView driver_card_layout,bus_card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        /*---------------------------------------------------Top Nav View-----------------------------------------------------*/
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
                Intent logoutIntent = new Intent(AdminDashboard.this,Login.class);
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



        /*---------------------------------------------------card views actions------------------------------------------------------*/
        driver_card_layout = findViewById(R.id.driver_card_layout);
        driver_card_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminDriverDataReturner();
            }
        });
        //bus_card.findViewById(R.id.buses_card);
        //bus_card.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {
              //  adminBusDataReturner();
            //}
        //});

        /*---------------------------------------------------Navigation Drawer actions------------------------------------------------------*/

        nav_View.bringToFront();
        nav_footer.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.menu);
        toggle.syncState();

        nav_View.setNavigationItemSelectedListener(this);
        nav_View.setCheckedItem(R.id.admin_home_nav);

        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);

                } else {
                    drawer.openDrawer(GravityCompat.START);
                    //showAdminData();
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

    public void adminProfileDataReturner(){
        Intent profile_intent = new Intent(AdminDashboard.this,AdminProfile.class);
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

    public void adminSettingsDataReturner(){
        Intent profile_intent = new Intent(AdminDashboard.this,Settings.class);
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
    public void adminDriverDataReturner(){
        Intent admin_Driver_intent = new Intent(AdminDashboard.this,AdminDriver.class);
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
    public void adminBusDataReturner(){
        Intent admin_Driver_intent = new Intent(AdminDashboard.this,AdminBus.class);
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
    public void adminCustomerDataReturner(){
        Intent admin_Driver_intent = new Intent(AdminDashboard.this,AdminCustomer.class);
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
    public void adminRoutesDataReturner(){
        Intent admin_Driver_intent = new Intent(AdminDashboard.this,BusRoutes.class);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.admin_home_nav:
                break;
            case R.id.admin_profile_nav:
                adminProfileDataReturner();
                break;
            case R.id.admin_notification_nav:
                break;
            case R.id.admin_driver_nav:
                adminDriverDataReturner();
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
    public void returnDrivers(View view){
        adminBusDataReturner();


    }
    public void viewCustomers(View view){
        adminCustomerDataReturner();
    }
    public void viewRoutes(View view){
            adminRoutesDataReturner();

    }

}
