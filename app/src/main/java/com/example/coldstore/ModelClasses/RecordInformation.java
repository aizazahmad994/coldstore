package com.example.coldstore.ModelClasses;

import java.io.Serializable;

public class RecordInformation implements Serializable {
    String Key;
    String date;
    String idByClient;
    String idByStore;
    String noOfSacks;
    String position;
    String rackNo;
    String roomNo;
    String typeOfPotato;
    String form;
String number;
    public RecordInformation()
    {

    }

    public RecordInformation(String Key,String form,String date, String idByClient, String id_by_store, String no_of_sacks, String position, String rack_no, String room_no, String type_of_potato, String number)
    {
        this.Key=Key;
        this.date = date;
        this.idByClient = idByClient;
        this.idByStore = id_by_store;
        this.noOfSacks = no_of_sacks;
        this.position = position;
        this.rackNo = rack_no;
        this.roomNo = room_no;
        this.typeOfPotato = type_of_potato;
        this.number= number;
        this.form =form;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getKey() {return Key;}

    public String getDate() {
        return date;
    }

    public String getIdByClient() {
        return idByClient;
    }

    public String getIdByStore() {
        return idByStore;
    }

    public String getNoOfSacks() {
        return noOfSacks;
    }

    public String getNumber() {return number;}

    public void setNumber(String number) {this.number = number;}

    public String getPosition() {
        return position;
    }

    public String getRackNo() {
        return rackNo;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public String getTypeOfPotato() {
        return typeOfPotato;
    }

    public void setKey(String key) {  this.Key = key;}

    public void setDate(String date) {
        this.date = date;
    }

    public void setIdByClient(String idByClient) {
        this.idByClient = idByClient;
    }

    public void setIdByStore(String idByStore) {
        this.idByStore = idByStore;
    }

    public void setNoOfSacks(String noOfSacks) {
        this.noOfSacks = noOfSacks;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setRackNo(String rackNo) {
        this.rackNo = rackNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public void setTypeOfPotato(String typeOfPotato) {
        this.typeOfPotato = typeOfPotato;
    }
}
