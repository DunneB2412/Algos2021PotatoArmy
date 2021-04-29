package com.potatoes_are_great.search;

import java.io.*;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Stop {
    private static final Pattern PATTERN = Pattern.compile("(\\d+),(\\d+),(.+),(.+),(-?\\d\\d\\d?.\\d*),(-?\\d\\d\\d?.\\d*),([A-Z][A-Z]\\s\\d\\d),(.*),(\\d),(\\d*)");
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

                }
            }
            reader.close();
            ready = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static boolean contains(Stop stop){
        return STOP_HASHTABLE.contains(stop);
    }
    public static Stop getFromId(int id){
        return STOP_HASHTABLE.get(id);
    }

    private final int id, code;
    private int locationType, parentStation;
    private String stopName, stopDesk, zoneId, stopURL;
    private GPSLocation gpsLocation;



    public Stop(String line){
        Matcher matcher = PATTERN.matcher(line);
        if(!matcher.matches()){
            throw new IllegalArgumentException("line passed in to stop, formatted incorrectly <"+line+">");
        }
        id = Integer.parseInt(matcher.group(1));
        code = Integer.parseInt(matcher.group(2));
        stopName = matcher.group(3);
        stopDesk = matcher.group(4);
        gpsLocation = new GPSLocation(Double.parseDouble(matcher.group(5)),Double.parseDouble(matcher.group(6)));
        zoneId = matcher.group(7);
        stopURL = matcher.group(8);
        locationType = Integer.parseInt(matcher.group(9));
        parentStation = Integer.parseInt(matcher.group(10));
        STOP_HASHTABLE.put(id,this);
    }
}
