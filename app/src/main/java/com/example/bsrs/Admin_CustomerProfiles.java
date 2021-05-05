package com.example.bsrs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;

public class Admin_CustomerProfiles extends AppCompatActivity {

    Toolbar toolbar;
    TextView omang_profile,fullname_profile,number_of_bookings;

    TextInputLayout gender,address,email,phone_number;
    ImageView editProfile;
    Button book;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_customer_profile);


        /*---------------------------------------------------profile data------------------------------------------------------*/
        omang_profile = findViewById(R.id.profile_omang);
        fullname_profile = findViewById(R.id.profile_full_name);
        gender = findViewById(R.id.cus_gender);
        address = findViewById(R.id.cus_address);
        email = findViewById(R.id.cus_email);
        phone_number = findViewById(R.id.cus_phone);
        editProfile = findViewById(R.id.edit_customer);
        number_of_bookings =findViewById(R.id.booking_number);
        book = findViewById(R.id.Book_customer);
        book.setVisibility(View.GONE);

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
                adminDriverDataReturner();
            }
        });

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

    public void adminEditAdminDataReturner(){
        Intent profile_intent = new Intent(Admin_CustomerProfiles.this,EditAdminProfile.class);
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
        Intent profile_intent = new Intent(Admin_CustomerProfiles.this,AdminProfile.class);
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



    public void callEditProfileListener(View view){
        Toast.makeText(this,"Editing of customers data is prohibited",Toast.LENGTH_LONG).show();
    }
    public void adminDriverDataReturner(){
        Intent admin_Driver_intent = new Intent(Admin_CustomerProfiles.this,AdminCustomer.class);
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

    public void callEditDriverProfileListener(View view) {
        Toast.makeText(this,"Editing of customers data is prohibited",Toast.LENGTH_LONG).show();
    }
    public void adminEditDriverDataReturner(){
        Intent profile_intent = new Intent(Admin_CustomerProfiles.this,EditDriverProfile.class);
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

    }
}
