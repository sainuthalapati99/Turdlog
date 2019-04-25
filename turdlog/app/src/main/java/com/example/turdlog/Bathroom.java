package com.example.turdlog;

public class Bathroom {

    private String name, address;
    private int date;

    public Bathroom(String name, String date, String address){

    }

    public Bathroom (String name, int Date, String address ) {
        this.name = name;
        this.date = date;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public int getDate() {
        return date;
    }

    public String getAddress() {
        return address;
    }
}