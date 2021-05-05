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

public class AdminCustomer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    NavigationView nav_View,nav_footer;
    Toolbar toolbar;
    TextView omang,adminName,username,toolbar_title;
    Button logout;
    RelativeLayout exit_app,settings;
    ArrayList<Customer> customer;
    Button addDrivers;
    private AdminCustomer_RecyclerView_Adapter adapter;
    private RecyclerView myRecycleView;
    private RecyclerView.LayoutManager mylayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_driver);
        initAllDrivers();
        /*---------------------------------------------------------------Add Button---------------------------------------------------------------------*/
        addDrivers = findViewById(R.id.add_drivers );
        addDrivers.setText("Booked Customers");

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
                Intent logoutIntent = new Intent(AdminCustomer.this,Login.class);
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
        toolbar_title.setText("Customers");
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
        Intent profile_intent = new Intent(AdminCustomer.this,Settings.class);
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
        Intent profile_intent = new Intent(AdminCustomer.this,AdminDashboard.class);
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

    private  void initAllDrivers(){
        //TODO:Initialise all drivers
        customer = new ArrayList<>();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference newsRef = rootRef.child("Customer");
        final ValueEventListener valueEventListener = new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Customer  myCustomer = new Customer();
                    //mydriver = ds.getValue(Driver.class);
                    //driver.add(mydriver);
                    myCustomer.setFullname(ds.child("fullname").getValue(String.class));
                    myCustomer.setGender(ds.child("gender").getValue(String.class));
                    myCustomer.setId(ds.child("id").getValue(String.class));

                    myCustomer.setAddress(ds.child("address").getValue(String.class));
                    myCustomer.setEmail(ds.child("email").getValue(String.class));
                    myCustomer.setPhone(ds.child("phone").getValue(String.class));
                    myCustomer.setPassword(ds.child("password").getValue(String.class));
                    myCustomer.setBooking(ds.child("booking").getValue(String.class));
                    Log.d(TAG,myCustomer.getBooking());
                    myCustomer.setUsername(ds.child("username").getValue(String.class));
                    customer.add(myCustomer);

                    //driver.add(new Driver(mydriver.getFullname(),mydriver.getUsername(),mydriver.getEmail(),mydriver.getPhone(),mydriver.getAddress(),
                    //  mydriver.getPassword(),mydriver.getGender(),mydriver.getId(),mydriver.getBus_plate_number(),mydriver.getRating()));
                    Log.d(TAG, String.valueOf(customer.get(0)));

                    myRecycleView = findViewById(R.id.adminDriver_Recyclerview);
                    myRecycleView.setHasFixedSize(true);
                    mylayout = new LinearLayoutManager(AdminCustomer.this);
                    adapter  = new AdminCustomer_RecyclerView_Adapter(customer);
                    myRecycleView.setLayoutManager(mylayout);
                    myRecycleView.setAdapter(adapter);
                    adapter.setOnitemClickListener(new AdminCustomer_RecyclerView_Adapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int postion) {
                            customerProfile(postion);
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

    public void customerProfile(int position){
        Customer cus = new Customer();
        Administration admin = new Administration();
        /*---------------------------------Driver--------------------------------------------*/
        cus.setUsername( customer.get(position).getUsername());
        cus.setId( customer.get(position).getId());
        Log.d(TAG,"RE FANO THLE SNUU " + customer.get(0));
        cus.setAddress( customer.get(position).getAddress());
        cus.setEmail( customer.get(position).getEmail());
        cus.setFullname( customer.get(position).getFullname());
        cus.setGender( customer.get(position).getGender());
        cus.setPassword( customer.get(position).getPassword());
        cus.setPhone( customer.get(position).getPhone());
        cus.setBooking(customer.get(position).getBooking());
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


        Intent admin_customerIntent = new Intent(getApplicationContext(), Admin_CustomerProfiles.class);
        /*---------------------------------Driver--------------------------------------------*/
        admin_customerIntent.putExtra("Cusfullname", cus.getFullname());
        admin_customerIntent.putExtra("Cusid", cus.getId());
        admin_customerIntent.putExtra("Cusemail", cus.getEmail());
        admin_customerIntent.putExtra("Cusaddress", cus.getAddress());
        admin_customerIntent.putExtra("Cusphone", cus.getPhone());
        admin_customerIntent.putExtra("Cususername", cus.getUsername());
        admin_customerIntent.putExtra("Cusgender", cus.getGender());
        admin_customerIntent.putExtra("Cuspassword", cus.getPassword());
        admin_customerIntent.putExtra("Cusbooking",cus.getBooking());
        /*---------------------------------Admin--------------------------------------------*/
        admin_customerIntent.putExtra("Adfullname",admin.getFullname());
        admin_customerIntent.putExtra("Adid",admin.getId());
        admin_customerIntent.putExtra("Ademail",admin.getEmail());
        admin_customerIntent.putExtra("Adaddress",admin.getAddress());
        admin_customerIntent.putExtra("Adgender",admin.getGender());
        admin_customerIntent.putExtra("Adphone",admin.getPhone());
        admin_customerIntent.putExtra("Adusername",admin.getUsername());


        startActivity(admin_customerIntent);

    }

   /* public void driverArray(){
        driver = initAllDrivers();
    }*/

    public void adminProfileDataReturner(){
        Intent profile_intent = new Intent(AdminCustomer.this,AdminProfile.class);
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
