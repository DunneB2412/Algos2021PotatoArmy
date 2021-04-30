package com.potatoes_are_great.search;

import com.potatoes_are_great.utill.Patterns;
import com.potatoes_are_great.utill.Time;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;

/**
 * @author duneb5
 */
public class Trip implements Iterable<Stop>{

    public static int getId(String line){
        Matcher matcher = Patterns.TRIP_PATTERN.matcher(line);
        if(!matcher.matches()){
            //return null;
            throw new IllegalArgumentException("invalid line <"+line+"> as input for trip");
        }
        return Integer.parseInt(matcher.group(1));
    }


    private final List<Stop> stops = new ArrayList<>();
    private final List<Time[]> times = new ArrayList<>();
    private final List<Double> dist = new ArrayList<>();
    private final List<Integer[]> pDTypes = new ArrayList<>();
    private final int id;

    public Trip(int id){
        this.id = id;
    }

    public void add(String line){
        assert getId(line)!=this.id: "line should only be added to a trip with the same id";
        Matcher matcher = Patterns.TRIP_PATTERN.matcher(line);

        matcher.matches();//we already know the matcher matches by now. unfortunately this is needed to form the groups

        Time arrival = Time.getTimeFromPattern(matcher.group(2), Patterns.TIME_PATTERN);
        Time departure = Time.getTimeFromPattern(matcher.group(3), Patterns.TIME_PATTERN);
        Time[] time = new Time[]{arrival, departure};
        this.times.add(time);
        Stop stop = Stop.getFromId(Integer.parseInt(matcher.group(4)));
        stops.add(stop);
        stop.RegisterTrip(this);
        Integer[] pDType = new Integer[]{Integer.parseInt(matcher.group(7)),Integer.parseInt(matcher.group(8))};
        this.pDTypes.add(pDType);
        dist.add(matcher.group(9).length()>0? Double.parseDouble(matcher.group(9)):0);
    }

    public int getId() {
        return id;
    }

    @Override
    public Iterator<Stop> iterator() {
        return stops.iterator();
    }

    public boolean contains(Stop stop){
        return stops.contains(stop);
    }

    public double getTrueDistance(Stop a, Stop b){
        if(contains(a)&&contains(b)){
            int ia = stops.indexOf(a);
            int ib = stops.indexOf(b);
            if(ia<=ib){
                return dist.get(ib)-dist.get(ia);
            }
        }
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Trip){
            Trip t = (Trip) obj;
            if(t.id == id) return true;
            for (int i = 0; i < stops.size(); i++) {
                if(stops.get(i).equals(t.stops.get(i)));
            }
            return true;
        }
        return false;
    }

    public Time[] getTimeWindow(){
        return new Time[]{times.get(0)[0],times.get(times.size()-1)[1]};
    }




//    private class Node implements Comparable<Node>{
//        private final Time[] times;
//        private final Stop stop;
//        private final int ordinal;
//
//        private Node[] links;
//        private double[] costs;
//
//        @Override
//        public int compareTo(Node o) {
//            return 0;
//        }
//    }
}
