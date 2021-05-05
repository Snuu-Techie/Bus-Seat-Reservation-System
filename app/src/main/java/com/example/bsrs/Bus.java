package com.example.bsrs;

import android.media.Image;

import java.util.Objects;

public class Bus {
    private String bus_plate_number;
    private String seats_Id;
    private String model;
    private String number_of_seats;
    private String route_id;
    private String timetable_id;
    private Image photo;

    public Bus() {
    }

    public Bus(String bus_plate_number, String seats_Id, String model, String number_of_seats, String route_id, String timetable_id) {
        this.bus_plate_number = bus_plate_number;
        this.seats_Id = seats_Id;
        this.model = model;
        this.number_of_seats = number_of_seats;
        this.route_id = route_id;
        this.timetable_id = timetable_id;
    }

    public String getBus_plate_number() {
        return bus_plate_number;
    }

    public void setBus_plate_number(String bus_plate_number) {
        this.bus_plate_number = bus_plate_number;
    }

    public String getSeats_Id() {
        return seats_Id;
    }

    public void setSeats_Id(String seats_Id) {
        this.seats_Id = seats_Id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumber_of_seats() {
        return number_of_seats;
    }

    public void setNumber_of_seats(String number_of_seats) {
        this.number_of_seats = number_of_seats;
    }

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

    public String getTimetable_id() {
        return timetable_id;
    }

    public void setTimetable_id(String timetable_id) {
        this.timetable_id = timetable_id;
    }

    public Image getPhoto() {
        return photo;
    }

    public void setPhoto(Image photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bus)) return false;
        Bus bus = (Bus) o;
        return Objects.equals(bus_plate_number, bus.bus_plate_number) &&
                Objects.equals(seats_Id, bus.seats_Id) &&
                Objects.equals(model, bus.model) &&
                Objects.equals(number_of_seats, bus.number_of_seats) &&
                Objects.equals(route_id, bus.route_id) &&
                Objects.equals(timetable_id, bus.timetable_id) &&
                Objects.equals(photo, bus.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bus_plate_number, seats_Id, model, number_of_seats, route_id, timetable_id, photo);
    }

    @Override
    public String toString() {
        return "Bus{" +
                "bus_plate_number='" + bus_plate_number + '\'' +
                ", seats_Id='" + seats_Id + '\'' +
                ", model='" + model + '\'' +
                ", number_of_seats='" + number_of_seats + '\'' +
                ", route_id='" + route_id + '\'' +
                ", timetable_id='" + timetable_id + '\'' +
                ", photo=" + photo +
                '}';
    }
}
