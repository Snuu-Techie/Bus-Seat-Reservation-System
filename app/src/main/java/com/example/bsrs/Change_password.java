package com.example.bsrs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Change_password extends AppCompatActivity {
    TextInputLayout mPassword,mConfirm_pass,mUsername;
    Spinner mUser_type;
    private String mUsernameString;
    private String mPasswordString;
    private String mAnother_pass_String;
    DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_activity);

        mPassword= findViewById(R.id.password);
        mConfirm_pass = findViewById(R.id.another_password);
        mUsername = findViewById(R.id.username);
        mUser_type = findViewById(R.id.users_spinner);
        fillSpinner();


    }

    private void fillSpinner() {
        ArrayList<String> users = new ArrayList<>();
        users.add("Customer");
        users.add("Driver");
        users.add("Admin");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, users);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mUser_type.setAdapter(adapter);

    }
    private boolean validatePassword() {
        mPasswordString= mPassword.getEditText().getText().toString().trim();
        mAnother_pass_String = mConfirm_pass.getEditText().getText().toString().trim();
       if(!mPasswordString.equals(mAnother_pass_String)){
           mConfirm_pass.setError("Passwords are not the same");
           return false;
       }else{
           mConfirm_pass.setError(null);
           mConfirm_pass.setEnabled(true);
           return true;
       }
    }


    public void change_Password(){
        Intent intent = getIntent();
        String usernameFromLogin = intent.getStringExtra("username");
        //mUsername.getEditText().setText(usernameFromLogin);
        mUsernameString= mUsername.getEditText().getText().toString().trim();
        mPasswordString= mPassword.getEditText().getText().toString().trim();
        mAnother_pass_String = mConfirm_pass.getEditText().getText().toString().trim();


        String selectedUser = mUser_type.getSelectedItem().toString();
        if (selectedUser.equals("Customer")) {
            mReference = FirebaseDatabase.getInstance().getReference("Customer");
            
if(mPasswordString.equals(mAnother_pass_String)) {
                mReference.child(mUsernameString).child("password").setValue(mAnother_pass_String);
                Toast.makeText(this,"Password was changed",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"Password was not changed.Try again",Toast.LENGTH_LONG).show();
            }

        }else if(selectedUser.equals("Driver")){
            mReference = FirebaseDatabase.getInstance().getReference("Driver");
            if(mPasswordString.equals(mAnother_pass_String)) {
                mReference.child(mUsernameString).child("password").setValue(mAnother_pass_String);
                Toast.makeText(this,"Password was changed",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"Password was not changed.Try again",Toast.LENGTH_LONG).show();
            }

        }else if(selectedUser.equals("Admin")){
            mReference = FirebaseDatabase.getInstance().getReference("Admin");
            if(mPasswordString.equals(mAnother_pass_String)) {
                mReference.child(mUsernameString).child("password").setValue(mAnother_pass_String);
                Toast.makeText(this,"Password was changed",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"Password was not changed.Try again",Toast.LENGTH_LONG).show();
            }

        }

    }
    public void changePassword(View view){
        if(!validatePassword()){
            return;
        }else{
            change_Password();
        }

    }
}
