package com.example.bsrs;

import java.util.Objects;

public class Booking {
    private String booking_id;
    private String bus_plate_number;
    private String seat_id;
    private String customer_id;
    private String customer_fullname;
    private String driver_fullname;
    private String date;
    private String seat_number;
    private String depature_time;
    private String from;
    private String to;

    public Booking(String booking_id, String bus_plate_number, String seat_id, String customer_id,
                   String customer_fullname, String driver_fullname, String date, String seat_number, String depature_time,
                   String from, String to) {
        this.booking_id = booking_id;
        this.bus_plate_number = bus_plate_number;
        this.seat_id = seat_id;
        this.customer_id = customer_id;
        this.customer_fullname = customer_fullname;
        this.driver_fullname = driver_fullname;
        this.date = date;
        this.depature_time = depature_time;
        this.from = from;
        this.to = to;
        this.seat_number = seat_number;
    }

    public Booking() {
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getSeat_number() {
        return seat_number;
    }

    public void setSeat_number(String seat_number) {
        this.seat_number = seat_number;
    }

    public String getBus_plate_number() {
        return bus_plate_number;
    }

    public void setBus_plate_number(String bus_plate_number) {
        this.bus_plate_number = bus_plate_number;
    }

    public String getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(String seat_id) {
        this.seat_id = seat_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_fullname() {
        return customer_fullname;
    }

    public void setCustomer_fullname(String customer_fullname) {
        this.customer_fullname = customer_fullname;
    }

    public String getDriver_fullname() {
        return driver_fullname;
    }

    public void setDriver_fullname(String driver_fullname) {
        this.driver_fullname = driver_fullname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDepature_time() {
        return depature_time;
    }

    public void setDepature_time(String depature_time) {
        this.depature_time = depature_time;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;
        Booking booking = (Booking) o;
        return Objects.equals(booking_id, booking.booking_id) &&
                Objects.equals(bus_plate_number, booking.bus_plate_number) &&
                Objects.equals(seat_id, booking.seat_id) &&
                Objects.equals(customer_id, booking.customer_id) &&
                Objects.equals(customer_fullname, booking.customer_fullname) &&
                Objects.equals(driver_fullname, booking.driver_fullname) &&
                Objects.equals(date, booking.date) &&
                Objects.equals(depature_time, booking.depature_time) &&
                Objects.equals(from, booking.from) &&
                Objects.equals(to, booking.to)&&
                Objects.equals(seat_number,booking.seat_number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(booking_id, bus_plate_number, seat_id, customer_id, customer_fullname, driver_fullname, date,seat_number ,depature_time, from, to);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "booking_id='" + booking_id + '\'' +
                ", bus_plate_number='" + bus_plate_number + '\'' +
                ", seat_id='" + seat_id + '\'' +
                ", customer_id='" + customer_id + '\'' +
                ", customer_fullname='" + customer_fullname + '\'' +
                ", driver_fullname='" + driver_fullname + '\'' +
                ", date='" + date + '\'' +
                ", depature_time='" + depature_time + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }

}
