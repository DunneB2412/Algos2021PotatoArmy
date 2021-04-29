package com.potatoes_are_great.search;

import java.util.Hashtable;

public class Schedule {
    private Hashtable<Integer, Trip> trips;
    public Schedule(){
        trips = new Hashtable<>();
    }

    public void addLine(String line){
        Integer id = Trip.getId(line);
        Trip trip;
        if((trip=trips.get(id))!=null) {
            trip.add(line);
        }else{
            trip = new Trip(id);
            trip.add(line);
            trips.put(id,trip);
        }
    }


}
