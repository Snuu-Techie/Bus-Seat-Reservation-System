package com.example.bsrs;

public class Customer extends Account {

    String booking;

    public Customer() {
    }

    public Customer(String fullname, String username, String email, String phone, String address, String password, String gender, String id, String booking) {
        super(fullname, username, email, phone, address, password, gender, id);
    }

    public String getBooking() {
        return booking;
    }

    public void setBooking(String booking) {
        this.booking = booking;
    }




}
