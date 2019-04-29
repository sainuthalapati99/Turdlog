package com.example.turdlog;

public class Bathroom {

    private String name;
    private int rating;
    private double lat, lang;

    public Bathroom(){

    }


    public Bathroom (String name, int rating, double lat, double lang) {
        this.name = name;
        this.rating = rating;
        this.lat = lat;
        this.lang = lang;

    }

    public String getName() {
        return name;
    }

    public int getRating() { return  rating; }

    public double getLat() { return lat; }

    public double getLang() { return lang; }


}