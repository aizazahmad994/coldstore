package com.example.coldstore.ModelClasses;

import java.io.Serializable;
public class UserInformation implements Serializable {
    String name;
    String address;
    String number;

    UserInformation()
    {

    }

    public UserInformation(String name, String address, String number) {
        this.name = name;
        this.address = address;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
