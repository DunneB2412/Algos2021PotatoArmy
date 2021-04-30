package com.potatoes_are_great.search;

import java.io.*;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * @author duneb5
 */
public class Schedule implements Iterable<Trip>{
    private Hashtable<Integer, Trip> trips;
    boolean ready;
    public Schedule(File file){
        trips = new Hashtable<>();
        ready = false;
        Schedule sc = this;
        //setUp(file);
        new Thread(() -> sc.setUp(file)).start();
    }

    private void setUp(File file) {
        try {
            while (!Stop.isReady());
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine())!=null) {
                try {
                    addLine(line);
                } catch (IllegalArgumentException ignored) {
                    System.out.println("Schedule:"+line);
                }
            }
            reader.close();
            ready = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
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


    @Override
    public Iterator<Trip> iterator() {
        return trips.values().iterator();
    }
}
