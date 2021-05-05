package com.example.bsrs;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateDriverAccounts extends AppCompatActivity {

    TextInputLayout fullname,username,email,phone,address,password,rating,busPlate,licence;
    int id;
    String myId;
    //Button signup;
    RadioGroup gender;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_driver_accounts);
        fullname = findViewById(R.id.regfullname);
        username = findViewById(R.id.regusername);
        email = findViewById(R.id.regemail);
        phone = findViewById(R.id.regphone_number);
        address = findViewById(R.id.regaddress);
        password = findViewById(R.id.regpassword);
        gender = findViewById(R.id.regGender);
        rating = findViewById(R.id.regrating);
        busPlate = findViewById(R.id.regBusid);
        licence = findViewById(R.id.regid);
        //signup = findViewById(R.id.signupBtn);


    }

    private boolean validateName() {
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
    }




    public void signUp(View view){
        if(!validateName() | !validatePassword() | !validatePhonenumber() | !validateEmail() | !validateUsername() | !validateAddress()){
            return;
        }
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Driver");


        //customer.setFullname
        String name = (fullname.getEditText().getText().toString());
        // customer.setUsername
        String myusername = (username.getEditText().getText().toString());
        //customer.setEmail
        String myemail =  (email.getEditText().getText().toString());
        //customer.setPhone
        String phonenum =  (phone.getEditText().getText().toString());
        //customer.setAddress
        String addres = (address.getEditText().getText().toString());
        //customer.setPassword
        String pass = (password.getEditText().getText().toString());
        String rate = (rating.getEditText().getText().toString());
        String busid = (busPlate.getEditText().getText().toString());
        String driverid = (licence.getEditText().getText().toString());


        int selectedId = gender.getCheckedRadioButtonId();
        RadioButton selectedRadioButton =  findViewById(selectedId);
        String selectedRadioButtonText = selectedRadioButton.getText().toString();
        //customer.setGender
        String gen = (selectedRadioButtonText);


        Driver driver = new Driver(name,myusername,myemail,phonenum,addres,pass,gen,driverid,busid,rate);

        reference.child(myusername).setValue(driver);
        Toast.makeText(this,"Your account has been created",Toast.LENGTH_LONG).show();


    }

}
