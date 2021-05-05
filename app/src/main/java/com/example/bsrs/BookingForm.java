package com.example.bsrs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class BookingForm extends AppCompatActivity {
    TextInputLayout mBooking_id,mbus_plate_number,mseat_id,mcustomer_id,mcustomer_fullname,mdriver_fullname,mdate,mseat_number,mtime,mfrom,mto;
    final Boolean _TRUE = true;
    private FirebaseDatabase mrootNode;
    private DatabaseReference mreference;
    Button book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);
        mBooking_id = findViewById(R.id.book_id);
        mbus_plate_number = findViewById(R.id.book_bus_plate_number);
        mseat_id = findViewById(R.id.book_seat_id);
        mcustomer_id = findViewById(R.id.book_customer_id);
        mcustomer_fullname = findViewById(R.id.book_customer_fullname);
        mdriver_fullname = findViewById(R.id.book_driver_fullname);
        mdate = findViewById(R.id.book_date);
        mseat_number =findViewById(R.id.book_seat_number);
        mtime = findViewById(R.id.book_time);
        mfrom = findViewById(R.id.book_from);
        mto = findViewById(R.id.book_to);
        book = findViewById(R.id.bookBtn);

        customer_details();
        intents_details();
        driver_details();
        schedule_details();
        route_details();

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBookingDetails();
            }
        });




    }

    private void addBookingDetails() {
        customer_bookUpdate();
        booking_Update();
        seat_Update();
        toConfirmDetails();

    }
    /*public void bookNow(View view){
        addBookingDetails();

    }*/
    private void customer_details(){
        Intent myintent =getIntent();
        final String user = myintent.getStringExtra("Cususername");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Customer");

        Query customer_user = reference.orderByChild("username").equalTo(user);
        customer_user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nameFromDB = dataSnapshot.child(user).child("fullname").getValue(String.class);
                String idFromDB = dataSnapshot.child(user).child("id").getValue(String.class);
                mcustomer_id.getEditText().setText(idFromDB);
                mcustomer_fullname.getEditText().setText(nameFromDB);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void intents_details(){
        Intent myintent = getIntent();
        String user = myintent.getStringExtra("Cususername");
        String number = myintent.getStringExtra("bookNumber");
        String plate =  myintent.getStringExtra("BusBusPlate");
        String seat_ids = myintent.getStringExtra("BusSeat_id");
        String booking_ids = plate + "-" + number;
        mBooking_id.getEditText().setText(booking_ids);
        mseat_number.getEditText().setText(number);
        mseat_id.getEditText().setText(seat_ids);
        mBooking_id.getEditText().setText(booking_ids);
        mbus_plate_number.getEditText().setText(plate);
    }
    private void driver_details(){
        Intent myintent = getIntent();
        final String plate =  myintent.getStringExtra("BusBusPlate");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Driver");
        Query customer_user = reference.orderByChild("bus_plate_number").equalTo(plate);

        customer_user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    String nameFromDB = ds.child("fullname").getValue(String.class);
                    mdriver_fullname.getEditText().setText(nameFromDB);


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    private void route_details(){
        Intent myintent = getIntent();
        final String plate =  myintent.getStringExtra("BusBusPlate");
        if(plate.equals("B000AAA") || plate.equals("B001BBB")||plate.equals("B002CCC") ||plate.equals("B003DDD")){
            String number = "01";
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Routes").child(number);
            Query checkUser = reference.orderByChild("bus_plate_number").equalTo(plate);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds: dataSnapshot.getChildren()) {
                        Route route = new Route();
                        route.setFrom(ds.child("from").getValue(String.class));
                        route.setTo(ds.child("to").getValue(String .class));
                        mfrom.getEditText().setText(route.getFrom());
                        mto.getEditText().setText(route.getTo());


                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        if(plate.equals("B004EEE") || plate.equals("B005FFF")){
            String number = "02";
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Routes").child(number);
            Query checkUser = reference.orderByChild("bus_plate_number").equalTo(plate);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds: dataSnapshot.getChildren()) {
                        Route route = new Route();
                        route.setFrom(ds.child("from").getValue(String.class));
                        route.setTo(ds.child("to").getValue(String .class));
                        mfrom.getEditText().setText(route.getFrom());
                        mto.getEditText().setText(route.getTo());


                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        if(plate.equals("B006GGG") || plate.equals("B007HHH")){
            String number = "03";
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Routes").child(number);
            Query checkUser = reference.orderByChild("bus_plate_number").equalTo(plate);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds: dataSnapshot.getChildren()) {
                        Route route = new Route();
                        route.setFrom(ds.child("from").getValue(String.class));
                        route.setTo(ds.child("to").getValue(String .class));
                        mfrom.getEditText().setText(route.getFrom());
                        mto.getEditText().setText(route.getTo());


                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        if(plate.equals("B008III") || plate.equals("B009JJJ")||plate.equals("B010KKK") ){
            String number = "04";
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Routes").child(number);
            Query checkUser = reference.orderByChild("bus_plate_number").equalTo(plate);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds: dataSnapshot.getChildren()) {
                        Route route = new Route();
                        route.setFrom(ds.child("from").getValue(String.class));
                        route.setTo(ds.child("to").getValue(String .class));
                        mfrom.getEditText().setText(route.getFrom());
                        mto.getEditText().setText(route.getTo());


                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        if(plate.equals("B011LLL") || plate.equals("B012MMM")){
            String number = "05";
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Routes").child(number);
            Query checkUser = reference.orderByChild("bus_plate_number").equalTo(plate);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds: dataSnapshot.getChildren()) {
                        Route route = new Route();
                        route.setFrom(ds.child("from").getValue(String.class));
                        route.setTo(ds.child("to").getValue(String .class));
                        mfrom.getEditText().setText(route.getFrom());
                        mto.getEditText().setText(route.getTo());


                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        if(plate.equals("B013NNN")){
            String number = "06";
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Routes").child(number);
            Query checkUser = reference.orderByChild("bus_plate_number").equalTo(plate);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds: dataSnapshot.getChildren()) {
                        Route route = new Route();
                        route.setFrom(ds.child("from").getValue(String.class));
                        route.setTo(ds.child("to").getValue(String .class));
                        mfrom.getEditText().setText(route.getFrom());
                        mto.getEditText().setText(route.getTo());


                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        if(plate.equals("B014OOO") ){
            String number = "07";
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Routes").child(number);
            Query checkUser = reference.orderByChild("bus_plate_number").equalTo(plate);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds: dataSnapshot.getChildren()) {
                        Route route = new Route();
                        route.setFrom(ds.child("from").getValue(String.class));
                        route.setTo(ds.child("to").getValue(String .class));
                        mfrom.getEditText().setText(route.getFrom());
                        mto.getEditText().setText(route.getTo());


                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        if(plate.equals("B015PPP") || plate.equals("B016QQQ")||plate.equals("B017RRR") ||plate.equals("B018SSS")|| plate.equals("B019TTT")){
            String number = "08";
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Routes").child(number);
            Query checkUser = reference.orderByChild("bus_plate_number").equalTo(plate);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds: dataSnapshot.getChildren()) {
                        Route route = new Route();
                        route.setFrom(ds.child("from").getValue(String.class));
                        route.setTo(ds.child("to").getValue(String .class));
                        mfrom.getEditText().setText(route.getFrom());
                        mto.getEditText().setText(route.getTo());


                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }

    }
    private void schedule_details(){
        Intent myintent = getIntent();
        final String plate =  myintent.getStringExtra("BusBusPlate");
        if(plate.equals("B000AAA") || plate.equals("B001BBB")||plate.equals("B002CCC") ||plate.equals("B003DDD")){
            String number = "01";
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Timetable").child(number);
            Query checkUser = reference.orderByChild("bus_plate_number").equalTo(plate);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds: dataSnapshot.getChildren()) {
                        Timetable time = new Timetable();
                        time.setDepature_date(ds.child("departure_date").getValue(String.class));
                        time.setTime(ds.child("time").getValue(String .class));
                        String [] date = time.getDeparture_date().split("-");
                        String my_date =  date [2] + "-" +  date [1]  + "-" +  date [0];
                        mdate.getEditText().setText(my_date);
                        mtime.getEditText().setText(time.getTime());

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        if(plate.equals("B004EEE") || plate.equals("B005FFF")){
            String number = "02";
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Timetable").child(number);
            Query checkUser = reference.orderByChild("bus_plate_number").equalTo(plate);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds: dataSnapshot.getChildren()) {
                        Timetable time = new Timetable();
                        time.setDepature_date(ds.child("departure_date").getValue(String.class));
                        time.setTime(ds.child("time").getValue(String .class));
                        String [] date = time.getDeparture_date().split("-");
                        String my_date =  date [2] + "-" +  date [1]  + "-" +  date [0];
                        mdate.getEditText().setText(my_date);
                        mtime.getEditText().setText(time.getTime());

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        if(plate.equals("B006GGG") || plate.equals("B007HHH")){
            String number = "03";
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Timetable").child(number);
            Query checkUser = reference.orderByChild("bus_plate_number").equalTo(plate);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds: dataSnapshot.getChildren()) {
                        Timetable time = new Timetable();
                        time.setDepature_date(ds.child("departure_date").getValue(String.class));
                        time.setTime(ds.child("time").getValue(String .class));
                        String [] date = time.getDeparture_date().split("-");
                        String my_date =  date [2] + "-" +  date [1]  + "-" +  date [0];
                        mdate.getEditText().setText(my_date);
                        mtime.getEditText().setText(time.getTime());

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        if(plate.equals("B008III") || plate.equals("B009JJJ")||plate.equals("B010KKK") ){
            String number = "04";
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Timetable").child(number);
            Query checkUser = reference.orderByChild("bus_plate_number").equalTo(plate);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds: dataSnapshot.getChildren()) {
                        Timetable time = new Timetable();
                        time.setDepature_date(ds.child("departure_date").getValue(String.class));
                        time.setTime(ds.child("time").getValue(String .class));
                        String [] date = time.getDeparture_date().split("-");
                        String my_date =  date [2] + "-" +  date [1]  + "-" +  date [0];
                        mdate.getEditText().setText(my_date);
                        mtime.getEditText().setText(time.getTime());

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        if(plate.equals("B011LLL") || plate.equals("B012MMM")){
            String number = "05";
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Timetable").child(number);
            Query checkUser = reference.orderByChild("bus_plate_number").equalTo(plate);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds: dataSnapshot.getChildren()) {
                        Timetable time = new Timetable();
                        time.setDepature_date(ds.child("departure_date").getValue(String.class));
                        time.setTime(ds.child("time").getValue(String .class));
                        String [] date = time.getDeparture_date().split("-");
                        String my_date =  date [2] + "-" +  date [1]  + "-" +  date [0];
                        mdate.getEditText().setText(my_date);
                        mtime.getEditText().setText(time.getTime());

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        if(plate.equals("B013NNN")){
            String number = "06";
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Timetable").child(number);
            Query checkUser = reference.orderByChild("bus_plate_number").equalTo(plate);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds: dataSnapshot.getChildren()) {
                        Timetable time = new Timetable();
                        time.setDepature_date(ds.child("departure_date").getValue(String.class));
                        time.setTime(ds.child("time").getValue(String .class));
                        String [] date = time.getDeparture_date().split("-");
                        String my_date =  date [2] + "-" +  date [1]  + "-" +  date [0];
                        mdate.getEditText().setText(my_date);
                        mtime.getEditText().setText(time.getTime());

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        if(plate.equals("B014OOO") ){
            String number = "07";
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Timetable").child(number);
            Query checkUser = reference.orderByChild("bus_plate_number").equalTo(plate);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds: dataSnapshot.getChildren()) {
                        Timetable time = new Timetable();
                        time.setDepature_date(ds.child("departure_date").getValue(String.class));
                        time.setTime(ds.child("time").getValue(String .class));
                        String [] date = time.getDeparture_date().split("-");
                        String my_date =  date [2] + "-" +  date [1]  + "-" +  date [0];
                        mdate.getEditText().setText(my_date);
                        mtime.getEditText().setText(time.getTime());

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        if(plate.equals("B015PPP") || plate.equals("B016QQQ")||plate.equals("B017RRR") ||plate.equals("B018SSS")|| plate.equals("B019TTT")){
            String number = "08";
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Timetable").child(number);
            Query checkUser = reference.orderByChild("bus_plate_number").equalTo(plate);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds: dataSnapshot.getChildren()) {
                        Timetable time = new Timetable();
                        time.setDepature_date(ds.child("departure_date").getValue(String.class));
                        time.setTime(ds.child("time").getValue(String .class));
                        String [] date = time.getDeparture_date().split("-");
                        String my_date =  date [2] + "-" +  date [1]  + "-" +  date [0];
                        mdate.getEditText().setText(my_date);
                        mtime.getEditText().setText(time.getTime());

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }
    private void customer_bookUpdate(){
        Intent myintent =getIntent();
        final String user = myintent.getStringExtra("Cususername");
        String booking_id = mBooking_id.getEditText().getText().toString();
        String bus_plate = mbus_plate_number.getEditText().getText().toString();
        String seat_id = mseat_id.getEditText().getText().toString();
        String customer_id = mcustomer_id.getEditText().getText().toString();
        String customer_fullname = mcustomer_fullname.getEditText().getText().toString();
        String driver_fullname = mdriver_fullname.getEditText().getText().toString();
        String booking_date = mdate.getEditText().getText().toString();
        String booking_seat_number = mseat_number.getEditText().getText().toString();
        String booking_time = mtime.getEditText().getText().toString();
        String booking_from= mfrom.getEditText().getText().toString();
        String booking_to = mto.getEditText().getText().toString();

        mrootNode = FirebaseDatabase.getInstance();
        mreference = mrootNode.getReference("Customer");
        mreference.child(user).child("isBooked").setValue(true);
        mreference.child(user).child("booking_id").setValue(bus_plate);



    }
    private void toConfirmDetails(){
        String booking_id = mBooking_id.getEditText().getText().toString();
        String bus_plate = mbus_plate_number.getEditText().getText().toString();
        String seat_id = mseat_id.getEditText().getText().toString();
        String customer_id = mcustomer_id.getEditText().getText().toString();
        String customer_fullname = mcustomer_fullname.getEditText().getText().toString();
        String driver_fullname = mdriver_fullname.getEditText().getText().toString();
        String booking_date = mdate.getEditText().getText().toString();
        String booking_seat_number = mseat_number.getEditText().getText().toString();
        String booking_time = mtime.getEditText().getText().toString();
        String booking_from= mfrom.getEditText().getText().toString();
        String booking_to = mto.getEditText().getText().toString();

        Intent intent = new Intent(this,ConfirmBooking.class);
        intent.putExtra("booking_id",booking_id);
        intent.putExtra("bus_plate",bus_plate);
        intent.putExtra("seat_id",seat_id);
        intent.putExtra("customer_id",customer_id);
        intent.putExtra("customer_fullname",customer_fullname);
        intent.putExtra("driver_fullname",driver_fullname);
        intent.putExtra("booking_date",booking_date);
        intent.putExtra("booking_seat_number",booking_seat_number);
        intent.putExtra("booking_time",booking_time);
        intent.putExtra("booking_from",booking_from);
        intent.putExtra("booking_to",booking_to);
        startActivity(intent);


    }
    private void booking_Update(){
        String booking_id = mBooking_id.getEditText().getText().toString();
        String bus_plate = mbus_plate_number.getEditText().getText().toString();
        String seat_id = mseat_id.getEditText().getText().toString();
        String customer_id = mcustomer_id.getEditText().getText().toString();
        String customer_fullname = mcustomer_fullname.getEditText().getText().toString();
        String driver_fullname = mdriver_fullname.getEditText().getText().toString();
        String booking_date = mdate.getEditText().getText().toString();
        String booking_seat_number = mseat_number.getEditText().getText().toString();
        String booking_time = mtime.getEditText().getText().toString();
        String booking_from= mfrom.getEditText().getText().toString();
        String booking_to = mto.getEditText().getText().toString();

        mrootNode = FirebaseDatabase.getInstance();
        mreference = mrootNode.getReference("Bookings");
        Booking booking = new Booking(booking_id,bus_plate,seat_id,customer_id,customer_fullname,
                driver_fullname,booking_date,booking_seat_number,booking_time,booking_from,booking_to);
        mreference.child(booking_id).setValue(booking);

    }
    private void seat_Update(){
        String booking_id = mBooking_id.getEditText().getText().toString();
        String bus_plate = mbus_plate_number.getEditText().getText().toString();
        String seat_id = mseat_id.getEditText().getText().toString();
        String customer_id = mcustomer_id.getEditText().getText().toString();
        String customer_fullname = mcustomer_fullname.getEditText().getText().toString();
        String driver_fullname = mdriver_fullname.getEditText().getText().toString();
        String booking_date = mdate.getEditText().getText().toString();
        String booking_seat_number = mseat_number.getEditText().getText().toString();
        String booking_time = mtime.getEditText().getText().toString();
        String booking_from= mfrom.getEditText().getText().toString();
        String booking_to = mto.getEditText().getText().toString();

        mrootNode = FirebaseDatabase.getInstance();
        mreference = mrootNode.getReference("Seat");
        mreference.child(seat_id).child(booking_seat_number).child("booked").setValue(true);
        mreference.child(seat_id).child(booking_seat_number).child("booking_Id").setValue(booking_id);
    }


}

