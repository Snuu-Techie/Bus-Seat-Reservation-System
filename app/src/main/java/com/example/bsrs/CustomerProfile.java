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

public class CustomerProfile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    NavigationView nav_View,nav_footer;
    Toolbar toolbar;
    TextView omang,cusName,username,omang_profile,fullname_profile,number_of_bookings;
    Button  logout;
    TextInputLayout gender,address,email,phone_number;
    ImageView editProfile;
    RelativeLayout exit_app,settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);


        /*---------------------------------------------------profile data------------------------------------------------------*/
        omang_profile = findViewById(R.id.profile_omang);
        fullname_profile = findViewById(R.id.profile_full_name);
        gender = findViewById(R.id.cus_gender);
        address = findViewById(R.id.cus_address);
        email = findViewById(R.id.cus_email);
        phone_number = findViewById(R.id.cus_phone);
        editProfile = findViewById(R.id.edit_customer);
        number_of_bookings =findViewById(R.id.booking_number);

        /*---------------------------------------------------Show profile data------------------------------------------------------*/
        showProfileData();

        /*---------------------------------------------------Top Nav Bar data------------------------------------------------------*/

        drawer = findViewById(R.id.drawer_layout);
        nav_View = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        View header = nav_View.getHeaderView(0);
        omang = header.findViewById(R.id.omang);
        cusName = header.findViewById(R.id.fullname);
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
        /*---------------------------------------------------LoggingOut------------------------------------------------------*/
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logoutIntent = new Intent(CustomerProfile.this,Login.class);
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
        nav_View.setCheckedItem(R.id.profile_nav );

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

    public  void showCustomerHeaderData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("Cusfullname");
        String usernameCus = intent.getStringExtra("Cususername");
        String omangCus = intent.getStringExtra("Cusid");

        username.setText(usernameCus);
        cusName.setText(name);
        omang.setText(omangCus);

    }
        private  void showProfileData(){
            Intent intent = getIntent();
            String cus_name = intent.getStringExtra("Cusfullname");
            String cus_omang = intent.getStringExtra("Cusid");
            String cus_email = intent.getStringExtra("Cusemail");
            String cus_address = intent.getStringExtra("Cusaddress");
            String cus_gender = intent.getStringExtra("Cusgender");
            String cus_phone = intent.getStringExtra("Cusphone");
            //String user = intent.getStringExtra("Cususername");
            String cus_booking = intent.getStringExtra("Cusbooking");

            fullname_profile.setText(cus_name);
            omang_profile.setText(cus_omang);
            gender.getEditText().setText(cus_gender);
            address.getEditText().setText(cus_address);
            phone_number.getEditText().setText(cus_phone);
            email.getEditText().setText(cus_email);
            number_of_bookings.setText(cus_booking);

    }

    public void customerHomeDataReturner(){
        Intent profile_intent = new Intent(CustomerProfile.this,CustomerDashboard.class);
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
        Intent profile_intent = new Intent(CustomerProfile .this,Settings.class);
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
    public void EditCustomerDataReturner(){
        Intent profile_intent = new Intent(CustomerProfile.this,EditCustomerProfile.class);
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

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();

        }
    }

    public void callEditProfileListener(View view){
        EditCustomerDataReturner();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.home_nav:
                customerHomeDataReturner();
                break;
            case R.id.profile_nav:
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
}
