package com.potatoes_are_great.search;

import com.potatoes_are_great.utill.Patterns;

import java.io.*;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;
import java.util.regex.Matcher;

/**
 * @author duneb5
 */
public class Stop implements Iterable<Trip>{
    private static final Hashtable<Integer, Stop> STOP_HASHTABLE = new Hashtable<>();
    private static boolean ready = true;
    public static boolean isReady(){
        return ready;
    }

    public static void prepare(File file){
        ready = false;
        STOP_HASHTABLE.clear();
        try {
            ready = true;
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine())!=null){
                try {
                    new Stop(line);
                }catch (Exception ignored){
                    System.out.println(line);
                }
            }
            reader.close();
            ready = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Stop getRandom() {
        Object[] arr = STOP_HASHTABLE.values().toArray();
        return (Stop) arr[Math.abs(new Random().nextInt())%arr.length];
    }

    public static boolean contains(Stop stop){
        return STOP_HASHTABLE.contains(stop);
    }
    public static Stop getFromId(int id){
        return STOP_HASHTABLE.get(id);
    }

    private final int id;
    private final Integer code;
    private int locationType;
    private Integer parentStation;
    private String stopName, stopDesk, zoneId, stopURL;
    private GPSLocation gpsLocation;
    private HashSet<Trip> trips;



    public Stop(String line){
        Matcher matcher = Patterns.STOP_PATTERN.matcher(line);
        if(!matcher.matches()){
            throw new IllegalArgumentException("line passed in to stop, formatted incorrectly <"+line+">");
        }
        id = Integer.parseInt(matcher.group(1));
        String codeS = matcher.group(2).replaceAll("\\s","");
        code = codeS.length()>0? Integer.parseInt(codeS): null;
        stopName = matcher.group(3);
        stopDesk = matcher.group(4);
        gpsLocation = new GPSLocation(Double.parseDouble(matcher.group(5)),Double.parseDouble(matcher.group(6)));
        zoneId = matcher.group(7);
        stopURL = matcher.group(8);
        locationType = Integer.parseInt(matcher.group(9));
        parentStation = matcher.group(10).length()>0? Integer.parseInt(matcher.group(10)):null;
        STOP_HASHTABLE.put(id,this);
        trips = new HashSet<>();
    }

    public void RegisterTrip(Trip trip){
        trips.add(trip);
    }

    @Override
    public Iterator<Trip> iterator() {
        return trips.iterator();
    }

    public GPSLocation getGpsLocation(){
        return gpsLocation;
    }
}
