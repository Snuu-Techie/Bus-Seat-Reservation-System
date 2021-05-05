package com.example.bsrs;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;

public class AdminProfile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    NavigationView nav_View,nav_footer;
    Toolbar toolbar;
    TextView omang,adminName,username,omang_profile,fullname_profile;
    Button  logout;
    TextInputLayout gender,address,email,phone_number;
    ImageView editProfile;
    RelativeLayout exit_app,settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);


        /*---------------------------------------------------profile data------------------------------------------------------*/
        omang_profile = findViewById(R.id.omang_profile);
        fullname_profile = findViewById(R.id.fullname_profile);
        gender = findViewById(R.id.admin_gender);
        address = findViewById(R.id.admin_address);
        email = findViewById(R.id.admin_email);
        phone_number = findViewById(R.id.admin_number);
        editProfile = findViewById(R.id.edit_admin);

        /*---------------------------------------------------Show profile data------------------------------------------------------*/
        showProfileData();

        /*---------------------------------------------------Top Nav Bar data------------------------------------------------------*/

        drawer = findViewById(R.id.drawer_layout);
        nav_View = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        View header = nav_View.getHeaderView(0);
        omang = header.findViewById(R.id.omang);
        adminName = header.findViewById(R.id.fullname);
        username = header.findViewById(R.id.username);
        logout = header.findViewById(R.id.logout);
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
        /*---------------------------------------------------LoggingOut------------------------------------------------------*/
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logoutIntent = new Intent(AdminProfile.this,Login.class);
                startActivity(logoutIntent);
            }
        });
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
        toggle.setHomeAsUpIndicator(R.drawable.menu_blue);
        toggle.syncState();

        nav_View.setNavigationItemSelectedListener(this);
        nav_View.setCheckedItem(R.id.admin_profile_nav );

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

    private void showAdminHeaderData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("Adfullname");
        String usernameAd = intent.getStringExtra("Adusername");
        String omangAd = intent.getStringExtra("Adid");

        username.setText(usernameAd);
        adminName.setText(name);
        omang.setText(omangAd);
    }


    private  void showProfileData(){
        Intent intent = getIntent();
        String name = intent.getStringExtra("Adfullname");
        String omangAd = intent.getStringExtra("Adid");
        String admin_address= intent.getStringExtra("Adaddress");
        String admin_phone = intent.getStringExtra("Adphone");
        String admin_email = intent.getStringExtra("Ademail");
        String admin_gender = intent.getStringExtra("Adgender");

        fullname_profile.setText(name);
        omang_profile.setText(omangAd);
        gender.getEditText().setText(admin_gender);

        address.getEditText().setText(admin_address);
        phone_number.getEditText().setText(admin_phone);
        email.getEditText().setText(admin_email);

    }

    public void adminHomeDataReturner(){
        Intent profile_intent = new Intent(AdminProfile.this,AdminDashboard.class);
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
    public void adminSettingsDataReturner(){
        Intent profile_intent = new Intent(AdminProfile.this,Settings.class);
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
    public void adminEditAdminDataReturner(){
        Intent profile_intent = new Intent(AdminProfile.this,EditAdminProfile.class);
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

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();

        }
    }

    public void callEditProfileListener(View view){
       adminEditAdminDataReturner();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.admin_home_nav:
                adminHomeDataReturner();
                break;
            case R.id.admin_profile_nav:
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
}
