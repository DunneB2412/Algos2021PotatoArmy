package com.potatoes_are_great.search;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Trip implements Iterable<Stop>{
    private static final Pattern PATTERN = Pattern.compile("(\\d+),(\\d+),(\\d\\d?:\\d\\d:\\d\\d),(\\d\\d?:\\d\\d:\\d\\d),(\\d+),(.*),(\\d),(\\d),(\\d*.?\\d*)"); //TODO repair last parretn

    public static int getId(String line){
        Matcher matcher = PATTERN.matcher(line);
        if(!matcher.matches()){
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
        Matcher matcher = PATTERN.matcher(line);
        //we already know the matcher matches by now.
        Time[] time = new Time[]{Time.valueOf(matcher.group(2)),Time.valueOf(matcher.group(3))};
        this.times.add(time);
        stops.add(Stop.getFromId(Integer.parseInt(matcher.group(4))));
        Integer[] pDType = new Integer[]{Integer.parseInt(matcher.group(7)),Integer.parseInt(matcher.group(8))};
        this.pDTypes.add(pDType);
        dist.add(matcher.group(9).length()>0? Double.parseDouble(matcher.group(9)):0);
    }

    @Override
    public Iterator<Stop> iterator() {
        return stops.iterator();
    }

    public boolean contains(Stop stop){
        return stops.contains(stop);
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
