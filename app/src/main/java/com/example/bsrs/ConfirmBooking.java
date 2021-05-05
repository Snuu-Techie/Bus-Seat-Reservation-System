package com.example.bsrs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class ConfirmBooking extends AppCompatActivity {
    TextView mBooking_id,mBusplate,mCustomer_id,mCustomer_fullname,mDriver_fullname,mdate,mtime,mseat_number;
    Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_booking);
        mBooking_id = findViewById(R.id.booking_id);
        mBusplate = findViewById(R.id.plate_number);
        mCustomer_id =findViewById(R.id.customer_id);
        mCustomer_fullname =findViewById(R.id.customer_fullname);
        mDriver_fullname = findViewById(R.id.driver_fullname);
        mdate = findViewById(R.id.date);
        mtime = findViewById(R.id.time);
        mseat_number =findViewById(R.id.seat_number);
        confirm = findViewById(R.id.confirmBtn);
        Intent intent = getIntent();
        mBooking_id.setText( intent.getStringExtra("booking_id"));
        mBusplate.setText(intent.getStringExtra("bus_plate"));
        mCustomer_id.setText(intent.getStringExtra("customer_id"));
        mCustomer_fullname.setText(intent.getStringExtra("customer_fullname"));
        mDriver_fullname.setText(intent.getStringExtra("driver_fullname"));
        mdate.setText(intent.getStringExtra("booking_date"));
        mseat_number.setText(intent.getStringExtra("booking_seat_number"));
        mtime.setText( intent.getStringExtra("booking_time"));
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmBook();
                takeScreenshot();

            }
        });


    }

    private void takeScreenshot() {
        Date date = new Date();
        CharSequence now = android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss",date);

            String filename = Environment.getExternalStorageDirectory()+ "/" + now + ".jpg";

            // create bitmap screen capture
            View rootView = getWindow().getDecorView().getRootView();
            rootView.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(rootView.getDrawingCache());
            rootView.setDrawingCacheEnabled(false);

            File imageFile = new File(filename);
            imageFile.getParentFile().mkdirs();

            try {

                FileOutputStream outputStream = new FileOutputStream(imageFile);
                int quality = 100;
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
                outputStream.flush();
                outputStream.close();

                Uri uri = Uri.fromFile(imageFile);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(uri, "image/*");
                startActivity(intent);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException eo){
                eo.printStackTrace();
            }

    }


    private void confirmBook() {

        confirm.setVisibility(View.GONE);
        Intent intent = getIntent();
        String booked_seat =  intent.getStringExtra("booking_seat_number");
        String from =  intent.getStringExtra("booking_from");
        String to = intent.getStringExtra("booking_to");;
        String customer_name = intent.getStringExtra("customer_fullname");
        String date = intent.getStringExtra("booking_date");
        String ref = intent.getStringExtra("booking_id");


        Intent thankyouintent = new Intent(this,ThankYou.class);
        thankyouintent.putExtra("booking_id",ref);
        thankyouintent.putExtra("customer_fullname",customer_name);
        thankyouintent.putExtra("booking_date",date);
        thankyouintent.putExtra("booking_seat_number",booked_seat);
        thankyouintent.putExtra("booking_from",from);
        thankyouintent.putExtra("booking_to",to);
        startActivity(thankyouintent);


    }
//    public  void confirmBook(View view){
//
  //      //confirmBook();
//
  //  }
}
