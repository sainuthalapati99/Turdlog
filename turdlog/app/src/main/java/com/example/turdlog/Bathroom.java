package com.example.turdlog;

public class Bathroom {

    private String name, address;
    private int date;

    public Bathroom(String name, String address){

    }

    public Bathroom (String name, int Date, String address ) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }


    public String getAddress() {
        return address;
    }
}