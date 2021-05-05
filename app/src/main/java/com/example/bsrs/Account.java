package com.example.bsrs;

import android.media.Image;
import android.provider.ContactsContract;
import android.widget.ImageView;

import java.util.Objects;

public abstract class Account {
    private String fullname;
    private String username;
    private String email;
    private String phone;
    private String address;
    private String password;
    private String gender;
    private String id;
    private Image photo;

    public Account() {
    }

    public Account(String fullname, String username, String email, String phone, String address, String password, String gender) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.gender = gender;
    }

    public Account(String fullname, String username, String email, String phone, String address, String password, String gender, String id) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.gender = gender;
        this.id = id;

    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return phone == account.phone &&
                Objects.equals(fullname, account.fullname) &&
                Objects.equals(username, account.username) &&
                Objects.equals(email, account.email) &&
                Objects.equals(address, account.address) &&
                Objects.equals(password, account.password) &&
                Objects.equals(gender, account.gender) &&
                Objects.equals(id, account.id) &&
                Objects.equals(photo, account.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullname, username, email, phone, address, password, gender, id, photo);
    }

    @Override
    public String toString() {
        return "Account{" +
                "fullname='" + fullname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", id='" + id + '\'' +
                ", photo=" + photo +
                '}';
    }
}
