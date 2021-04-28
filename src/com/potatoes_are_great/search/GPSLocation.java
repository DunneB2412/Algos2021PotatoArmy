package com.potatoes_are_great.search;

public class GPSLocation {
    private final double latitude, longitude;

    public GPSLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getDist(GPSLocation o){
        return Math.sqrt(Math.abs(Math.pow(latitude-o.latitude,2)+Math.pow(longitude-o.longitude,2))); //rather nieve approach, but it works
    }
}
