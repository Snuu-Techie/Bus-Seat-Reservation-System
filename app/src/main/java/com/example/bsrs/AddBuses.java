package com.example.bsrs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddBuses extends AppCompatActivity {
    TextInputLayout seat_id,model,number_of_seats,timetable,route,busPlate;
    int id;
    String myId;
    //Button signup;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_buses);

        seat_id = findViewById(R.id.regSeat_Id);
        model = findViewById(R.id.regmodelBus);
        number_of_seats = findViewById(R.id.regSeat_number);
        timetable = findViewById(R.id.regtimetable);
        route = findViewById(R.id.regroute);
        busPlate = findViewById(R.id.regBusid);

        /*---------------------------------------------------Top Nav Bar data------------------------------------------------------*/

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.back_button);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddBuses.this,AdminBus.class));

            }
        });

    }

    /*private boolean validateName() {
        String value = fullname.getEditText().getText().toString();
        if (value.isEmpty()) {
            fullname.setError("Field cannot be empty");
            return false;
        } else {
            fullname.setError(null);
            fullname.setEnabled(false);
            return true;
        }
    }
    private boolean validateUsername() {
        String value = username.getEditText().getText().toString();
        //String noWhiteSpace = "\\\\A\\\\w{4,20}\\\\z";

        if (value.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        }else if(value.length() >= 16){
            username.setError("Username too long");
            return false;
            //}else if (!value.matches(noWhiteSpace)) {
            //username.setError("White Spaces are not allowed");
            //return false;
        }
        else {
            username.setError(null);
            username.setEnabled(false);
            return true;
        }
    }
    private boolean validateEmail() {
        String value = email.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (value.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        }else if (!value.matches(emailPattern)) {
            email.setError("Invalid email address");
            return false;
        }
        else {
            email.setError(null);
            email.setEnabled(false);
            return true;
        }
    }
    private boolean validatePhonenumber() {
        String value = phone.getEditText().getText().toString();
        // String noWhiteSpace = "\\\\A\\\\w{4,20}\\\\z";
        int botsPhoneNumber = 8;

        if (value.isEmpty()) {
            phone.setError("Field cannot be empty");
            return false;
        }//else if (!value.matches(noWhiteSpace)) {
        //phone.setError("White Spaces are not allowed");
        //return false;}
        else if (value.length() > botsPhoneNumber | value.length() < botsPhoneNumber) {
            phone.setError("Unknown Number");
            return false;
        }
        else {
            phone.setError(null);
            phone.setEnabled(false);
            return true;
        }
    }
    private boolean validateAddress() {
        String value = address.getEditText().getText().toString();
        if (value.isEmpty()) {
            address.setError("Field cannot be empty");
            return false;
        } else {
            address.setError(null);
            address.setEnabled(false);
            return true;
        }
    }
    private boolean validatePassword() {
        String value = password.getEditText().getText().toString();
        int passwordVal = 10;
        // "^" +
        //"(?=.*[0-9])" +         //at least 1 digit
        //"(?=.*[a-z])" +         //at least 1 lower case letter
        //"(?=.*[A-Z])";//+         //at least 1 upper case letter
        //"(?=.*[a-zA-Z])" +      //any letter
        // "(?=.*[@#$%^&+=])" +    //at least 1 special character
        //"(?=\\S+$)" +           //no white spaces
        //".{4,}" +               //at least 4 characters
        //"$"";

        if (value.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        }else if (value.length() < passwordVal){
            password.setError("Password is too weak");
            return false;
        }
        else {
            password.setError(null);
            password.setEnabled(false);
            return true;
        }
    }*/




    public void AddBus(View view){
        /*if(!validateName() | !validatePassword() | !validatePhonenumber() | !validateEmail() | !validateUsername() | !validateAddress()){
            return;
        }*/
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Bus");


        //customer.setFullname
        String mymodel  = (model.getEditText().getText().toString());
        // customer.setUsername
        String myseatid = (seat_id.getEditText().getText().toString());
        //customer.setEmail
        String mytimetable =  (timetable.getEditText().getText().toString());
        //customer.setPhone
        String myroute=  (route.getEditText().getText().toString());
        //customer.setAddress
        String numberOfSeats = (number_of_seats.getEditText().getText().toString());
        //customer.setPassword
        String busid = (busPlate.getEditText().getText().toString());


        Bus bus = new Bus(busid,myseatid,mymodel,numberOfSeats,myroute,mytimetable);

        reference.child(busid).setValue(bus);
        Toast.makeText(this,"New Bus has been added",Toast.LENGTH_LONG).show();

        reference = rootNode.getReference("Seat");
        Seat seat = new Seat();
        seat.setSeats_Id(myseatid);
        reference.child(seat.getSeats_Id());

        for(int i = 1; i < 33; i++){
            seat.setSeat_number(Integer.toString(i));
            seat.setBooking_Id("empty");
            seat.setBooked(false);

            Seat myseating = new Seat(seat.getBooked(),seat.getSeats_Id());
            reference.child(seat.getSeats_Id()).child(seat.getSeat_number()).setValue(myseating);
        }


    }

}

